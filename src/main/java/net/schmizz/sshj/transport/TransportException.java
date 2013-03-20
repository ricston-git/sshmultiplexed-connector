/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.transport;

import net.schmizz.concurrent.ExceptionChainer;
import net.schmizz.sshj.common.DisconnectReason;
import net.schmizz.sshj.common.SSHException;

/** Transport-layer exception */
public class TransportException
        extends SSHException {

    /** @see ExceptionChainer */
    public static final ExceptionChainer<TransportException> chainer = new ExceptionChainer<TransportException>() {
        @Override
        public TransportException chain(Throwable t) {
            if (t instanceof TransportException)
                return (TransportException) t;
            else
                return new TransportException(t);
        }
    };

    public TransportException(DisconnectReason code) {
        super(code);
    }

    public TransportException(DisconnectReason code, String message) {
        super(code, message);
    }

    public TransportException(DisconnectReason code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public TransportException(DisconnectReason code, Throwable cause) {
        super(code, cause);
    }

    public TransportException(String message) {
        super(message);
    }

    public TransportException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransportException(Throwable cause) {
        super(cause);
    }

}