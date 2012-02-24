package org.mule.modules.ssh.multiplexer;

import org.apache.log4j.Logger;

/**
 * 
 * @author marianogonzalez
 *
 */
public abstract class SshThread extends Thread {

	private static final Logger logger = Logger.getLogger(SshThread.class);
	
	private SshClient client;

	public SshThread(SshClient client) {
		super();
		this.client = client;
	}
	
	public SshClient getClient() {
		return client;
	}

	public void setClient(SshClient client) {
		this.client = client;
	}
	
	protected void handleException(Throwable t) {
		String msg = "Error in consumer/producer thread";
		
		if (logger.isDebugEnabled()) {
			logger.error(msg, t);
		}
		
		throw new RuntimeException(msg, t);
	}

}
