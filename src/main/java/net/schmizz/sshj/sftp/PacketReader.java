/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.sftp;

import net.schmizz.concurrent.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PacketReader
        extends Thread {

    /** Logger */
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final InputStream in;
    private final Map<Long, Promise<Response, SFTPException>> promises = new ConcurrentHashMap<Long, Promise<Response, SFTPException>>();
    private final SFTPPacket<Response> packet = new SFTPPacket<Response>();
    private final byte[] lenBuf = new byte[4];
    private final SFTPEngine engine;

    public PacketReader(SFTPEngine engine) {
        this.engine = engine;
        this.in = engine.getSubsystem().getInputStream();
        setName("sftp reader");
    }

    private void readIntoBuffer(byte[] buf, int off, int len)
            throws IOException {
        int count = 0;
        int read = 0;
        while (count < len && ((read = in.read(buf, off + count, len - count)) != -1))
            count += read;
        if (read == -1)
            throw new SFTPException("EOF while reading packet");
    }

    private int getPacketLength()
            throws IOException {
        readIntoBuffer(lenBuf, 0, lenBuf.length);

        final long len = (lenBuf[0] << 24 & 0xff000000L
                | lenBuf[1] << 16 & 0x00ff0000L
                | lenBuf[2] << 8 & 0x0000ff00L
                | lenBuf[3] & 0x000000ffL);

        if (len > SFTPPacket.MAX_SIZE) {
        	throw new IllegalStateException("Invalid packet: indicated length "+len+" too large");
        }

        return (int) len;
    }

    public SFTPPacket<Response> readPacket()
            throws IOException {
        final int len = getPacketLength();
        packet.clear();
        packet.ensureCapacity(len);
        readIntoBuffer(packet.array(), 0, len);
        packet.wpos(len);
        return packet;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                readPacket();
                handle();
            }
        } catch (IOException e) {
            for (Promise<Response, SFTPException> promise : promises.values())
                promise.deliverError(e);
        }
    }

    public void handle()
            throws SFTPException {
        Response resp = new Response(packet, engine.getOperativeProtocolVersion());
        Promise<Response, SFTPException> promise = promises.remove(resp.getRequestID());
        log.debug("Received {} packet", resp.getType());
        if (promise == null)
            throw new SFTPException("Received [" + resp.readType() + "] response for request-id " + resp.getRequestID()
                                            + ", no such request was made");
        else
            promise.deliver(resp);
    }

    public Promise<Response, SFTPException> expectResponseTo(long requestId) {
        final Promise<Response, SFTPException> promise
                = new Promise<Response, SFTPException>("sftp / " + requestId, SFTPException.chainer);
        promises.put(requestId, promise);
        return promise;
    }

}
