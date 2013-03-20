/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package org.mule.modules.ssh.multiplexer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 
 * @author mariano.gonzalez@mulesoft.com
 *
 */
public class CallbackOutputStream extends ByteArrayOutputStream {

	private SshConnector connector;
	
	public CallbackOutputStream(SshConnector connector, int bufSize) {
		super(bufSize);
		this.connector = connector;
	}
	
	@Override
	public synchronized void write(int b) {
		super.write(b);
		if (b == '\n') {
			try {
				this.flush();
			} catch (IOException e) {
				throw new RuntimeException("Exception while flushing callback stream", e);
			}
		}
	}
	
	@Override
	public synchronized void flush() throws IOException {
		this.connector.doCallback(new String(this.buf));
		this.reset();
	}	
	

}
