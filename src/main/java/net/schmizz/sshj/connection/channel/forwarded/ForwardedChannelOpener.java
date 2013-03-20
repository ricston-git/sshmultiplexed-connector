/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.connection.channel.forwarded;

import net.schmizz.sshj.common.SSHPacket;
import net.schmizz.sshj.connection.ConnectionException;
import net.schmizz.sshj.transport.TransportException;

/** Takes care of handling {@code SSH_MSG_CHANNEL_OPEN} requests for forwarded channels of a specific type. */
public interface ForwardedChannelOpener {

    /** Returns the name of the channel type this opener can next. */
    String getChannelType();

    /**
     * Delegates a {@code SSH_MSG_CHANNEL_OPEN} request for the channel type claimed by this opener.
     *
     * @param buf {@link SSHPacket} containg the request except for the message identifier and channel type field
     */
    void handleOpen(SSHPacket buf)
            throws ConnectionException, TransportException;

}
