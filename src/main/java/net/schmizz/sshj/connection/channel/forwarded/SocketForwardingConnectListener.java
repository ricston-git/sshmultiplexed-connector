/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.connection.channel.forwarded;

import net.schmizz.concurrent.Event;
import net.schmizz.sshj.common.StreamCopier;
import net.schmizz.sshj.connection.channel.Channel;
import net.schmizz.sshj.connection.channel.SocketStreamCopyMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.TimeUnit;

/** A {@link ConnectListener} that forwards what is received over the channel to a socket and vice-versa. */
public class SocketForwardingConnectListener
        implements ConnectListener {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    protected final SocketAddress addr;

    /** Create with a {@link SocketAddress} this listener will forward to. */
    public SocketForwardingConnectListener(SocketAddress addr) {
        this.addr = addr;
    }

    /** On connect, confirm the channel and start forwarding. */
    @Override
    public void gotConnect(Channel.Forwarded chan)
            throws IOException {
        log.debug("New connection from {}:{}", chan.getOriginatorIP(), chan.getOriginatorPort());

        final Socket sock = new Socket();
        sock.setSendBufferSize(chan.getLocalMaxPacketSize());
        sock.setReceiveBufferSize(chan.getRemoteMaxPacketSize());

        sock.connect(addr);

        // ok so far -- could connect, let's confirm the channel
        chan.confirm();

        final Event<IOException> soc2chan = new StreamCopier(sock.getInputStream(), chan.getOutputStream())
                .bufSize(chan.getRemoteMaxPacketSize())
                .spawnDaemon("soc2chan");

        final Event<IOException> chan2soc = new StreamCopier(chan.getInputStream(), sock.getOutputStream())
                .bufSize(chan.getLocalMaxPacketSize())
                .spawnDaemon("chan2soc");

        SocketStreamCopyMonitor.monitor(5, TimeUnit.SECONDS, chan2soc, soc2chan, chan, sock);
    }

}
