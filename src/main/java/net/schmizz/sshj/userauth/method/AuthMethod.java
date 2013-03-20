/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.userauth.method;

import net.schmizz.sshj.common.SSHPacketHandler;
import net.schmizz.sshj.transport.TransportException;
import net.schmizz.sshj.userauth.AuthParams;
import net.schmizz.sshj.userauth.UserAuthException;

/** An authentication method of the <a href="http://www.ietf.org/rfc/rfc4252.txt">SSH Authentication Protocol</a>. */
public interface AuthMethod
        extends SSHPacketHandler {

    /** @return assigned name of this authentication method */
    String getName();

    /**
     * This method must be called before requesting authentication with this method.
     *
     * @param params parameters needed for authentication
     */
    void init(AuthParams params);

    /**
     * @throws UserAuthException  if there is an error with the request
     * @throws TransportException if there is a transport-related error
     */
    void request()
            throws UserAuthException, TransportException;

    /** @return whether authentication should be reattempted if it failed. */
    boolean shouldRetry();

}
