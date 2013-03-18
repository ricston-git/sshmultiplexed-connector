/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

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
			logger.debug(msg, t);
		}
		logger.error(msg);
		
		throw new RuntimeException(msg, t);
	}

}
