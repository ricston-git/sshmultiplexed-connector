/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.connection.channel.forwarded;

import net.schmizz.sshj.common.IOUtils;
import net.schmizz.sshj.connection.Connection;
import net.schmizz.sshj.connection.channel.Channel;
import net.schmizz.sshj.connection.channel.OpenFailException;
import net.schmizz.sshj.transport.TransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/** Base class for {@link ForwardedChannelOpener}'s. */
public abstract class AbstractForwardedChannelOpener
        implements ForwardedChannelOpener {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    protected final String chanType;
    protected final Connection conn;

    protected AbstractForwardedChannelOpener(String chanType, Connection conn) {
        this.chanType = chanType;
        this.conn = conn;
    }

    @Override
    public String getChannelType() {
        return chanType;
    }

    /** Calls the listener with the new channel in a separate thread. */
    protected void callListener(final ConnectListener listener, final Channel.Forwarded chan) {
        new Thread() {

            {
                setName("chanopener");
            }

            @Override
            public void run() {
                try {
                    listener.gotConnect(chan);
                } catch (IOException logged) {
                    log.warn("In callback to {}: {}", listener, logged);
                    if (chan.isOpen())
                        IOUtils.closeQuietly(chan);
                    else
                        try {
                            chan.reject(OpenFailException.Reason.CONNECT_FAILED, "");
                        } catch (TransportException cantdonthn) {
                            log.warn("Error rejecting {}: {}", chan, cantdonthn);
                        }
                }
            }

        }.start();
    }

}