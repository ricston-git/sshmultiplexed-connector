/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package org.mule.modules.ssh.multiplexer;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.trilead.ssh2.ChannelCondition;
import com.trilead.ssh2.Session;

/**
 * 
 * @author marianogonzalez
 *
 */
public class ResponseConsumerThread extends SshThread {

	private static final Logger logger = Logger.getLogger(ResponseConsumerThread.class);
	
	public ResponseConsumerThread(SshClient client) {
		super(client);
	}
	
	@Override
	public void run() {
		SshClient client = this.getClient();
		Session session = client.getSession();
		
		try {
			// The SSH ChannelInputStream is unbuffered, buffer to improve performance.
			InputStream stdout = new BufferedInputStream(client.getReceiver());
			InputStream stderr = new BufferedInputStream(client.getError());

			BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(stdout),client.getReceiverBufferSize());
			BufferedReader stderrReader = new BufferedReader(new InputStreamReader(stderr),client.getReceiverBufferSize());
			
			// Do this until an end of stream is encountered.
			while (true)
			{
				// Need to verify in this loop for STDOUT and STDERR. Without checking STDERR the session
				// could block after a few errors until its STDERR buffer is cleared.
				if ((stdout.available() == 0) && (stderr.available() == 0))
				{
					/* Even though currently there is no data available, it may be that new data arrives
					 * and the session's underlying channel is closed before we call waitForCondition().
					 * This means that EOF and STDOUT_DATA (or STDERR_DATA, or both) may
					 * be set together.
					 */
					int conditions = session.waitForCondition(
							ChannelCondition.STDOUT_DATA | 
							ChannelCondition.STDERR_DATA | 
							ChannelCondition.EOF, 
							2000);

					/* Wait no longer than 2 seconds (= 2000 milliseconds) */
					if ((conditions & ChannelCondition.TIMEOUT) != 0)
					{
						/* A timeout occured. */
						continue;
					}

					/* Here we do not need to check separately for CLOSED, since CLOSED implies EOF */
					if ((conditions & ChannelCondition.EOF) != 0)
					{
						/* The remote side won't send us further data... */
						if ((conditions & (ChannelCondition.STDOUT_DATA | ChannelCondition.STDERR_DATA)) == 0)
						{
							/* ... and we have consumed all data in the local arrival window. */
							break;
						}
					}

					/* OK, either STDOUT_DATA or STDERR_DATA (or both) is set. */
				}

				// Don't just check for stdoutReader.ready(), otherwise while(true) loop will be executed continuously until a newline character is encountered.
				while (stdout.available() > 0)
				{
					// Using buffered reader conveniently reads whole lines.
					// It blocks until it encounters a new line.
					String line = stdoutReader.readLine();
					if (line!=null && line.length()>0) {
						client.callback(line);
					}
				}

				while (stderr.available() > 0)
				{
					String line = stderrReader.readLine();
					if (line!=null && line.length()>0) {
						logger.warn("Error on connection '" + client.getDetails().getUsername() + "@" + client.getDetails().getHost() + "' : " + line);
						client.callback(line);
					}
				}
			}
		} 
		catch (IOException e) {
			this.handleException(e);
		} 
		finally {
			// Disconnect properly, releasing resources.
			logger.warn("Releasing client connection '" + client.getDetails().getUsername() + "@" + client.getDetails().getHost() + "'");
			client.disconnect();
		}
	}
}
