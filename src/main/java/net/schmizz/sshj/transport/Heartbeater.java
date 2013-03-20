/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.transport;

import net.schmizz.sshj.common.Message;
import net.schmizz.sshj.common.SSHPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class Heartbeater
        extends Thread {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final TransportImpl trans;

    private int interval;

    Heartbeater(TransportImpl trans) {
        this.trans = trans;
        setName("heartbeater");
    }

    synchronized void setInterval(int interval) {
        this.interval = interval;
        if (interval > 0 && getState() == Thread.State.NEW)
            start();
        notify();
    }

    synchronized int getInterval() {
        return interval;
    }

    synchronized private int getPositiveInterval()
            throws InterruptedException {
        while (interval <= 0)
            wait();
        return interval;
    }

    @Override
    public void run() {
        log.debug("Starting");
        try {
            while (!isInterrupted()) {
                final int hi = getPositiveInterval();
                if (trans.isRunning()) {
                    log.debug("Sending heartbeat since {} seconds elapsed", hi);
                    trans.write(new SSHPacket(Message.IGNORE));
                }
                Thread.sleep(hi * 1000);
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
