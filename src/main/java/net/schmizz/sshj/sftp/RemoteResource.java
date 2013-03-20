/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.sftp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public abstract class RemoteResource
        implements Closeable {

    /** Logger */
    protected final Logger log = LoggerFactory.getLogger(getClass());

    protected final Requester requester;
    protected final String path;
    protected final String handle;

    protected RemoteResource(Requester requester, String path, String handle) {
        this.requester = requester;
        this.path = path;
        this.handle = handle;
    }

    public String getPath() {
        return path;
    }

    protected Request newRequest(PacketType type) {
        return requester.newRequest(type).putString(handle);
    }

    @Override
    public void close()
            throws IOException {
        log.debug("Closing `{}`", this);
        requester.request(newRequest(PacketType.CLOSE))
                .retrieve(requester.getTimeoutMs(), TimeUnit.MILLISECONDS)
                .ensureStatusPacketIsOK();
    }

    @Override
    public String toString() {
        return "RemoteResource{" + path + "}";
    }

}
