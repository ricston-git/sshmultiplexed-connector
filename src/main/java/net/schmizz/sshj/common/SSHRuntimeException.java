/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.common;

/** Represents unrecoverable exceptions in the {@code net.schmizz.sshj} package. */
public class SSHRuntimeException
        extends RuntimeException {

    public SSHRuntimeException() {
        this(null, null);
    }

    public SSHRuntimeException(String message) {
        this(message, null);
    }

    public SSHRuntimeException(String message, Throwable cause) {
        super(message);
        if (cause != null)
            initCause(cause);
    }

    public SSHRuntimeException(Throwable cause) {
        this(null, cause);
    }

}
