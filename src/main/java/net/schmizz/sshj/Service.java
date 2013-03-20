/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj;

import net.schmizz.sshj.common.ErrorNotifiable;
import net.schmizz.sshj.common.SSHException;
import net.schmizz.sshj.common.SSHPacketHandler;
import net.schmizz.sshj.transport.TransportException;

/** Represents a service running on top of the SSH transport layer. */
public interface Service
        extends SSHPacketHandler, ErrorNotifiable {

    /** @return the assigned name for this SSH service. */
    String getName();

    /**
     * Notifies this service that a {@code SSH_MSG_UNIMPLEMENTED} was received for packet with given sequence number.
     * Meant to be invoked as a callback by the transport layer.
     *
     * @param seqNum sequence number of the packet which the server claims is unimplemented
     *
     * @throws SSHException if the packet is unexpected and may represent a disruption
     */
    void notifyUnimplemented(long seqNum)
            throws SSHException;

    /**
     * Request and install this service with the associated transport. Implementations should aim to make this method
     * idempotent by first checking the {@link net.schmizz.sshj.transport.Transport#getService()}  currently active
     * service}.
     *
     * @throws TransportException if there is an error sending the service request
     */
    void request()
            throws TransportException;

}