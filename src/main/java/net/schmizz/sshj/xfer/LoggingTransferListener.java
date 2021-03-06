/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.xfer;

import net.schmizz.sshj.common.StreamCopier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class LoggingTransferListener
        implements TransferListener {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final String relPath;

    public LoggingTransferListener() {
        this("");
    }

    private LoggingTransferListener(String relPath) {
        this.relPath = relPath;
    }

    @Override
    public TransferListener directory(String name) {
        log.debug("started transferring directory `{}`", name);
        return new LoggingTransferListener(relPath + name + "/");
    }

    @Override
    public StreamCopier.Listener file(final String name, final long size) {
        final String path = relPath + name;
        log.debug("started transferring file `{}` ({} bytes)", path, size);
        return new StreamCopier.Listener() {
            @Override
            public void reportProgress(long transferred)
                    throws IOException {
                if (log.isTraceEnabled()) {
                    log.trace("transferred {}% of `{}`", ((transferred * 100) / size), path);
                }
            }
        };
    }

}
