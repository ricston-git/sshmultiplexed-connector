/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package org.mule.modules.ssh.multiplexer;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * DTO object to carry the connection information
 * @author marianogonzalez
 *
 */
public class SshConnectionDetails {

	private String host;
	private int port;
	private String username;
	private String password;
	private int timeout;
	private boolean shellMode;
	private int receiverBufferSize;
	private SshMultiplexedConnector sshMultiplexedConnector;
	
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public void setSshMultiplexedConnector(
			SshMultiplexedConnector sshMultiplexedConnector) {
		this.sshMultiplexedConnector = sshMultiplexedConnector;
	}
	
	public SshMultiplexedConnector getSshMultiplexedConnector() {
		return sshMultiplexedConnector;
	}
	
	public boolean isShellMode() {
		return shellMode;
	}
	public void setShellMode(boolean shellMode) {
		this.shellMode = shellMode;
	}
	
	public int getReceiverBufferSize() {
		return receiverBufferSize;
	}
	public void setReceiverBufferSize(int receiverBufferSize) {
		this.receiverBufferSize = receiverBufferSize;
	}
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
}
