/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.userauth;

import net.schmizz.sshj.transport.Transport;

/** The parameters available to authentication methods. */
public interface AuthParams {

    /** @return name of the next service being requested */
    String getNextServiceName();

    /**
     * @return the transport which will allow sending packets; retrieving information like the session-id, remote
     *         host/port etc. which is needed by some method.
     */
    Transport getTransport();

    /** @return all userauth requests need to include the username */
    String getUsername();

}