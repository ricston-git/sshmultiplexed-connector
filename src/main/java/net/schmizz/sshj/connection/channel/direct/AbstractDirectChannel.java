/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.connection.channel.direct;

import net.schmizz.sshj.common.Buffer;
import net.schmizz.sshj.common.Message;
import net.schmizz.sshj.common.SSHPacket;
import net.schmizz.sshj.connection.Connection;
import net.schmizz.sshj.connection.ConnectionException;
import net.schmizz.sshj.connection.channel.AbstractChannel;
import net.schmizz.sshj.connection.channel.Channel;
import net.schmizz.sshj.connection.channel.OpenFailException;
import net.schmizz.sshj.transport.TransportException;

import java.util.concurrent.TimeUnit;

/** Base class for direct channels whose open is initated by the client. */
public abstract class AbstractDirectChannel
        extends AbstractChannel
        implements Channel.Direct {

    protected AbstractDirectChannel(Connection conn, String type) {
        super(conn, type);

        /*
        * We expect to receive channel open confirmation/rejection and want to be able to next this packet.
        */
        conn.attach(this);
    }

    @Override
    public void open()
            throws ConnectionException, TransportException {
        trans.write(buildOpenReq());
        openEvent.await(conn.getTimeout(), TimeUnit.SECONDS);
    }

    private void gotOpenConfirmation(SSHPacket buf)
            throws ConnectionException {
        try {
            init(buf.readUInt32AsInt(), buf.readUInt32(), buf.readUInt32());
        } catch (Buffer.BufferException be) {
            throw new ConnectionException(be);
        }
        openEvent.set();
    }

    private void gotOpenFailure(SSHPacket buf)
            throws ConnectionException {
        try {
            openEvent.deliverError(new OpenFailException(getType(), buf.readUInt32AsInt(), buf.readString()));
        } catch (Buffer.BufferException be) {
            throw new ConnectionException(be);
        }
        finishOff();
    }

    protected SSHPacket buildOpenReq() {
        return new SSHPacket(Message.CHANNEL_OPEN)
                .putString(getType())
                .putUInt32(getID())
                .putUInt32(getLocalWinSize())
                .putUInt32(getLocalMaxPacketSize());
    }

    @Override
    protected void gotUnknown(Message cmd, SSHPacket buf)
            throws ConnectionException, TransportException {
        switch (cmd) {

            case CHANNEL_OPEN_CONFIRMATION:
                gotOpenConfirmation(buf);
                break;

            case CHANNEL_OPEN_FAILURE:
                gotOpenFailure(buf);
                break;

            default:
                super.gotUnknown(cmd, buf);
        }
    }

}