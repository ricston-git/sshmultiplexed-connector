/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.connection.channel.forwarded;

import net.schmizz.sshj.common.Buffer;
import net.schmizz.sshj.common.SSHPacket;
import net.schmizz.sshj.connection.Connection;
import net.schmizz.sshj.connection.ConnectionException;
import net.schmizz.sshj.transport.TransportException;

/**
 * Handles forwarded {@code x11} channels. The actual request to forward X11 should be made from the specific {@link
 * net.schmizz.sshj.connection.channel.direct.Session Session}.
 */
public class X11Forwarder
        extends AbstractForwardedChannelOpener {

    /** An {@code x11} forwarded channel. */
    public static class X11Channel
            extends AbstractForwardedChannel {

        public static final String TYPE = "x11";

        public X11Channel(Connection conn,
                          int recipient, long remoteWinSize, long remoteMaxPacketSize,
                          String origIP, int origPort) {
            super(conn, TYPE, recipient, remoteWinSize, remoteMaxPacketSize, origIP, origPort);
        }

    }

    private final ConnectListener listener;

    /**
     * @param conn     connection layer
     * @param listener listener which will be delegated {@link X11Channel}'s to next
     */
    public X11Forwarder(Connection conn, ConnectListener listener) {
        super(X11Channel.TYPE, conn);
        this.listener = listener;
    }

    /** Internal API */
    @Override
    public void handleOpen(SSHPacket buf)
            throws ConnectionException, TransportException {
        try {
            callListener(listener, new X11Channel(conn,
                                                  buf.readUInt32AsInt(), buf.readUInt32(), buf.readUInt32(),
                                                  buf.readString(), buf.readUInt32AsInt()));
        } catch (Buffer.BufferException be) {
            throw new ConnectionException(be);
        }
    }

    /** Stop handling {@code x11} channel open requests. De-registers itself with connection layer. */
    public void stop() {
        conn.forget(this);
    }

}