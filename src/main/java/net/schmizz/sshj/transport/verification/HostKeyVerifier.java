/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.transport.verification;

import java.security.PublicKey;

/** Host key verification interface. */
public interface HostKeyVerifier {

    /**
     * This callback is invoked when the server's host key needs to be verified. The return value indicates to the
     * caller whether the SSH connection should proceed.
     * <p/>
     * <strong>Note</strong>: host key verification is the basis for security in SSH, therefore exercise due caution in
     * implementing!
     *
     * @param hostname remote hostname
     * @param port     remote port
     * @param key      host key of server
     *
     * @return {@code true} if key is acceptable, {@code false} otherwise
     */
    boolean verify(String hostname, int port, PublicKey key);

}
