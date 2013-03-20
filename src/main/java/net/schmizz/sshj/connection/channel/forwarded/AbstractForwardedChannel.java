/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.connection.channel.forwarded;

import net.schmizz.sshj.common.Message;
import net.schmizz.sshj.connection.Connection;
import net.schmizz.sshj.connection.channel.AbstractChannel;
import net.schmizz.sshj.connection.channel.Channel;
import net.schmizz.sshj.connection.channel.OpenFailException.Reason;
import net.schmizz.sshj.transport.TransportException;

/** Base class for forwarded channels whose open is initiated by the server. */
public abstract class AbstractForwardedChannel
        extends AbstractChannel
        implements Channel.Forwarded {

    protected final String origIP;
    protected final int origPort;

    /*
    * First 2 args are standard; the others can be parsed from a CHANNEL_OPEN packet.
    */

    protected AbstractForwardedChannel(Connection conn, String type,
                                       int recipient, long remoteWinSize, long remoteMaxPacketSize,
                                       String origIP, int origPort) {
        super(conn, type);
        this.origIP = origIP;
        this.origPort = origPort;
        init(recipient, remoteWinSize, remoteMaxPacketSize);
    }

    @Override
    public void confirm()
            throws TransportException {
        log.debug("Confirming `{}` channel #{}", getType(), getID());
        // Must ensure channel is attached before confirming, data could start coming in immediately!
        conn.attach(this);
        trans.write(newBuffer(Message.CHANNEL_OPEN_CONFIRMATION)
                            .putUInt32(getID())
                            .putUInt32(getLocalWinSize())
                            .putUInt32(getLocalMaxPacketSize()));
        openEvent.set();
    }

    @Override
    public void reject(Reason reason, String message)
            throws TransportException {
        log.debug("Rejecting `{}` channel: {}", getType(), message);
        conn.sendOpenFailure(getRecipient(), reason, message);
    }

    @Override
    public String getOriginatorIP() {
        return origIP;
    }

    @Override
    public int getOriginatorPort() {
        return origPort;
    }

}