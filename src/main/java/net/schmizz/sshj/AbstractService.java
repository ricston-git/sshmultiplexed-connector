/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj;

import net.schmizz.sshj.common.DisconnectReason;
import net.schmizz.sshj.common.Message;
import net.schmizz.sshj.common.SSHException;
import net.schmizz.sshj.common.SSHPacket;
import net.schmizz.sshj.transport.Transport;
import net.schmizz.sshj.transport.TransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** An abstract class for {@link Service} that implements common or default functionality. */
public abstract class AbstractService
        implements Service {

    /** Logger */
    protected final Logger log = LoggerFactory.getLogger(getClass());

    /** Assigned name of this service */
    protected final String name;
    /** Transport layer */
    protected final Transport trans;
    /** Timeout for blocking operations */
    protected int timeout;

    public AbstractService(String name, Transport trans) {
        this.name = name;
        this.trans = trans;
        timeout = trans.getTimeout();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void handle(Message msg, SSHPacket buf)
            throws SSHException {
        trans.sendUnimplemented();
    }

    @Override
    public void notifyError(SSHException error) {
        log.debug("Notified of {}", error.toString());
    }

    @Override
    public void notifyUnimplemented(long seqNum)
            throws SSHException {
        throw new SSHException(DisconnectReason.PROTOCOL_ERROR, "Unexpected: SSH_MSG_UNIMPLEMENTED");
    }

    @Override
    public void request()
            throws TransportException {
        final Service active = trans.getService();
        if (!equals(active))
            if (name.equals(active.getName()))
                trans.setService(this);
            else
                trans.reqService(this);
    }

    public int getTimeout() {
        return this.timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

}
