package org.mule.modules.ssh.multiplexer;

/**
 * 
 * @author marianogonzalez
 *
 */
public class ResponseProducerThread extends SshThread {
	
	public ResponseProducerThread(SshClient client) {
		super(client);
	}
	
	@Override
	public void run() {
		SshClient client = this.getClient();
		try {
			
			while (client.isConnected()) {
				String payload = client.getReceiverBuffer().take();
				client.callback(payload);
			} 
		} catch (InterruptedException e) {
			this.handleException(e);
		}
	}

}
