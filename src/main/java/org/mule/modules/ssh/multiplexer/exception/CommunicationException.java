package org.mule.modules.ssh.multiplexer.exception;

/**
 * Exception to represent an issue in the communication with a 
 * ssh host. The error can ben in the actual transport of the
 * message or the connection initialization as well.
 * 
 * @author marianogonzalez
 *
 */
public class CommunicationException extends Exception {

	private static final long serialVersionUID = 1L;

	public CommunicationException(String msg, Throwable t) {
		super(msg, t);
	}
	
}
