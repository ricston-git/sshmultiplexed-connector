package org.mule.modules.ssh.multiplexer;

import java.util.HashMap;
import java.util.Map;

import org.mule.modules.ssh.multiplexer.exception.CommunicationException;

/**
 * Important component to handle the connections abstracting the connector
 * from that.
 * 
 * It lazyly inits the connections on demmand and keeps a reusable instance of
 * {@org.mule.modules.ssh.multiplexer.SshClient} to represent the fact that a user
 * can keep the session open for a while.
 * 
 * @author marianogonzalez
 *
 */
public class SshConnectionManager {

	/**
	 * map to relate a username to a {@link org.mule.modules.ssh.multiplexer.SshClient}
	 * holding his session
	 */
	private Map<String, SshClient> clients = new HashMap<String, SshClient>();
	
	/**
	 * Gets a connection associated with the username specified in the details
	 * If the connection already exist it's reused. Otherwise a new one is created.
	 * 
	 * In case of needing a brand new connection opened, then
	 * {@link org.mule.modules.ssh.multiplexer.SshConnectionManager.openConnection(SshConnectionDetails)}
	 * is used and the connection obtained tracked
	 * 
	 * @see org.mule.modules.ssh.multiplexer.SshConnectionManager.openConnection(SshConnectionDetails)
	 * @param details - connection information
	 * @return an active and ready to use sshClient
	 * @throws CommunicationException
	 */
	public SshClient getConnection(SshConnectionDetails details) throws CommunicationException {
		SshClient client = this.clients.get(details.getUsername());
		
		if (client == null) {
			client = new SshClient(details);
			this.clients.put(details.getUsername(), client);
		}
		
		return client;
	}
	
	/**
	 * if an active connection associated with the username exists
	 * it will be released after calling this method
	 * @param username - the username whose connection we want to free
	 */
	public void release(String username) {
		SshClient client = this.clients.get(username);
		
		if (client != null) {
			client.disconnect();
			this.clients.remove(username);
		}
	}
	
	/**
	 * Releases all active connections form all users
	 */
	public void releaseAll() {
		for (SshClient client : this.clients.values()) {
			client.disconnect();
		}
		this.clients.clear();
	}
}
