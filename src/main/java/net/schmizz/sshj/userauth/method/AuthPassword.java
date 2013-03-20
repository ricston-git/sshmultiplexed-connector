/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.userauth.method;

import net.schmizz.sshj.common.Message;
import net.schmizz.sshj.common.SSHPacket;
import net.schmizz.sshj.transport.TransportException;
import net.schmizz.sshj.userauth.UserAuthException;
import net.schmizz.sshj.userauth.password.AccountResource;
import net.schmizz.sshj.userauth.password.PasswordFinder;

/** Implements the {@code password} authentication method. Password-change request handling is not currently supported. */
public class AuthPassword
        extends AbstractAuthMethod {

    private final PasswordFinder pwdf;

    public AuthPassword(PasswordFinder pwdf) {
        super("password");
        this.pwdf = pwdf;
    }

    @Override
    public SSHPacket buildReq()
            throws UserAuthException {
        final AccountResource accountResource = makeAccountResource();
        log.debug("Requesting password for {}", accountResource);
        return super.buildReq() // the generic stuff
                .putBoolean(false) // no, we are not responding to a CHANGEREQ
                .putSensitiveString(pwdf.reqPassword(accountResource));
    }

    @Override
    public void handle(Message cmd, SSHPacket buf)
            throws UserAuthException, TransportException {
        if (cmd == Message.USERAUTH_60)
            throw new UserAuthException("Password change request received; unsupported operation");
        else
            super.handle(cmd, buf);
    }

    /**
     * Returns {@code true} if the associated {@link PasswordFinder} tells that we should retry with a new password that
     * it will supply.
     */
    @Override
    public boolean shouldRetry() {
        return pwdf.shouldRetry(makeAccountResource());
    }

}