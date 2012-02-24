package org.mule.modules.ssh.multiplexer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

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
		BufferedReader reader = new BufferedReader(new InputStreamReader(client.getReceiver()));
		
		try {
			while (client.isConnected()) {
				char[] buf = new char[client.getReceiverBufferSize()];
				int read = reader.read(buf);
				
				if (read == -1 && client.isConnected()) {
					if (logger.isDebugEnabled()) {
						logger.error("Error reading from channel");
					}
				}
				
				client.getReceiverBuffer().put(new String(buf));
			}
		} catch (IOException e) {
			this.handleException(e);
		} catch (InterruptedException e) {
			this.handleException(e);
		}
	}
	
}
