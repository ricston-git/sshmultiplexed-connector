/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.common;

import net.schmizz.concurrent.ExceptionChainer;

import java.io.IOException;

/**
 * Most exceptions in the {@code net.schmizz.sshj} package are instances of this class. An {@link SSHException} is
 * itself an {@link IOException} and can be caught like that if this level of granularity is not desired.
 */
public class SSHException
        extends IOException {

    public static final ExceptionChainer<SSHException> chainer = new ExceptionChainer<SSHException>() {

        @Override
        public SSHException chain(Throwable t) {
            if (t instanceof SSHException)
                return (SSHException) t;
            else
                return new SSHException(t);
        }

    };

    private final DisconnectReason reason;

    public SSHException(DisconnectReason code) {
        this(code, null, null);
    }

    public SSHException(DisconnectReason code, String message) {
        this(code, message, null);
    }

    public SSHException(DisconnectReason code, String message, Throwable cause) {
        super(message);
        this.reason = code;
        if (cause != null)
            initCause(cause);
    }

    public SSHException(DisconnectReason code, Throwable cause) {
        this(code, null, cause);
    }

    public SSHException(String message) {
        this(DisconnectReason.UNKNOWN, message, null);
    }

    public SSHException(String message, Throwable cause) {
        this(DisconnectReason.UNKNOWN, message, cause);
    }

    public SSHException(Throwable cause) {
        this(DisconnectReason.UNKNOWN, null, cause);
    }

    public DisconnectReason getDisconnectReason() {
        return reason;
    }

    @Override
    public String getMessage() {
        if (super.getMessage() != null)
            return super.getMessage();
        else if (getCause() != null && getCause().getMessage() != null)
            return getCause().getMessage();
        else
            return null;
    }

    @Override
    public String toString() {
        final String cls = getClass().getName();
        final String code = reason != DisconnectReason.UNKNOWN ? "[" + reason + "] " : "";
        final String msg = getMessage() != null ? getMessage() : "";
        return cls + (code.isEmpty() && msg.isEmpty() ? "" : ": ") + code + msg;
    }

}
