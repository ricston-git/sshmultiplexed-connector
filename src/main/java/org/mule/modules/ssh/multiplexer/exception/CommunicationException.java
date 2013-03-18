/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

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
