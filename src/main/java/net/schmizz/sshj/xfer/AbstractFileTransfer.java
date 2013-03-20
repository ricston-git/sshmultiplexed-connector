/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.xfer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractFileTransfer {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    public static final LoggingTransferListener LOGGING_TRANSFER_LISTENER = new LoggingTransferListener();

    private volatile TransferListener transferListener = LOGGING_TRANSFER_LISTENER;

    public TransferListener getTransferListener() {
        return transferListener;
    }

    public void setTransferListener(TransferListener transferListener) {
        this.transferListener = (transferListener == null) ? LOGGING_TRANSFER_LISTENER : transferListener;
    }

}