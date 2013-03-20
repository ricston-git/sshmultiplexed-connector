/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.transport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public final class Reader
        extends Thread {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final TransportImpl trans;

    public Reader(TransportImpl trans) {
        this.trans = trans;
        setName("reader");
    }

    @Override
    public void run() {
        try {

            final Decoder decoder = trans.getDecoder();
            final InputStream inp = trans.getConnInfo().in;

            final byte[] recvbuf = new byte[decoder.getMaxPacketLength()];

            int needed = 1;

            while (!isInterrupted()) {
                int read = inp.read(recvbuf, 0, needed);
                if (read == -1)
                    throw new TransportException("Broken transport; encountered EOF");
                else
                    needed = decoder.received(recvbuf, read);
            }

        } catch (Exception e) {
            if (isInterrupted()) {
                // We are meant to shut up and draw to a close if interrupted
            } else
                trans.die(e);
        }

        log.debug("Stopping");
    }

}
