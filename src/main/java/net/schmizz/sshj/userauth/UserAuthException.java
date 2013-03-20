/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.userauth;

import net.schmizz.concurrent.ExceptionChainer;
import net.schmizz.sshj.common.DisconnectReason;
import net.schmizz.sshj.common.SSHException;

/** User authentication exception */
public class UserAuthException
        extends SSHException {

    public static final ExceptionChainer<UserAuthException> chainer = new ExceptionChainer<UserAuthException>() {

        @Override
        public UserAuthException chain(Throwable t) {
            if (t instanceof UserAuthException)
                return (UserAuthException) t;
            else
                return new UserAuthException(t);
        }

    };

    public UserAuthException(DisconnectReason code) {
        super(code);
    }

    public UserAuthException(DisconnectReason code, String message) {
        super(code, message);
    }

    public UserAuthException(DisconnectReason code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public UserAuthException(DisconnectReason code, Throwable cause) {
        super(code, cause);
    }

    public UserAuthException(String message) {
        super(message);
    }

    public UserAuthException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAuthException(Throwable cause) {
        super(cause);
    }

}
