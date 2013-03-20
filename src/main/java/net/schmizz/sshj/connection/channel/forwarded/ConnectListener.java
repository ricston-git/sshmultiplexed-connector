/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.connection.channel.forwarded;

import net.schmizz.sshj.connection.channel.Channel;

import java.io.IOException;

/** A connect listener is just that: it listens for new forwarded channels and can be delegated charge of them. */
public interface ConnectListener {

    /**
     * Notify this listener of a new forwarded channel. An implementation should firstly confirm or reject that
     * channel.
     *
     * @param chan the  forwarded channel
     *
     * @throws IOException if there is a problem handling the channel
     */
    void gotConnect(Channel.Forwarded chan)
            throws IOException;

}
