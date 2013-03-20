/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.connection;

import net.schmizz.concurrent.ExceptionChainer;
import net.schmizz.sshj.common.DisconnectReason;
import net.schmizz.sshj.common.SSHException;

/** Connection-layer exception. */
public class ConnectionException
        extends SSHException {

    public static final ExceptionChainer<ConnectionException> chainer = new ExceptionChainer<ConnectionException>() {
        @Override
        public ConnectionException chain(Throwable t) {
            if (t instanceof ConnectionException)
                return (ConnectionException) t;
            else
                return new ConnectionException(t);
        }
    };

    public ConnectionException(DisconnectReason code) {
        super(code);
    }

    public ConnectionException(DisconnectReason code, String message) {
        super(code, message);
    }

    public ConnectionException(DisconnectReason code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public ConnectionException(DisconnectReason code, Throwable cause) {
        super(code, cause);
    }

    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionException(Throwable cause) {
        super(cause);
    }

}