/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.common;

/**
 * An interface for classes to which packet handling may be delegated. Chains of such delegations may be used, e.g.
 * {@code packet decoder -> (SSHPacketHandler) transport layer -> (SSHPacketHandler) connection layer ->
 * (SSHPacketHandler) channel}.
 */
public interface SSHPacketHandler {

    /**
     * Delegate handling of some SSH packet to this object.
     *
     * @param msg the SSH {@link Message message identifier}
     * @param buf {@link SSHPacket} containing rest of the request
     *
     * @throws SSHException if there is a non-recoverable error
     */
    void handle(Message msg, SSHPacket buf)
            throws SSHException;

}
