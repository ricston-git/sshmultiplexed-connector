/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.sftp;

import net.schmizz.concurrent.ExceptionChainer;
import net.schmizz.sshj.common.DisconnectReason;
import net.schmizz.sshj.common.SSHException;
import net.schmizz.sshj.sftp.Response.StatusCode;

public class SFTPException
        extends SSHException {

    public static final ExceptionChainer<SFTPException> chainer = new ExceptionChainer<SFTPException>() {

        @Override
        public SFTPException chain(Throwable t) {
            if (t instanceof SFTPException)
                return (SFTPException) t;
            else
                return new SFTPException(t);
        }

    };

    public SFTPException(DisconnectReason code) {
        super(code);
    }

    public SFTPException(DisconnectReason code, String message) {
        super(code, message);
    }

    public SFTPException(DisconnectReason code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public SFTPException(DisconnectReason code, Throwable cause) {
        super(code, cause);
    }

    public SFTPException(String message) {
        super(message);
    }

    public SFTPException(String message, Throwable cause) {
        super(message, cause);
    }

    public SFTPException(Throwable cause) {
        super(cause);
    }

    private StatusCode sc;

    public StatusCode getStatusCode() {
        return (sc == null) ? StatusCode.UNKNOWN : sc;
    }

    public SFTPException(StatusCode sc, String msg) {
        this(msg);
        this.sc = sc;
    }

}
