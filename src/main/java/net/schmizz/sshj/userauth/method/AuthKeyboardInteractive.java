/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.userauth.method;

import net.schmizz.sshj.common.Buffer;
import net.schmizz.sshj.common.Message;
import net.schmizz.sshj.common.SSHPacket;
import net.schmizz.sshj.transport.TransportException;
import net.schmizz.sshj.userauth.UserAuthException;

/** Implements the {@code keyboard-interactive} authentication method. */
public class AuthKeyboardInteractive
        extends AbstractAuthMethod {

    private final ChallengeResponseProvider provider;

    public AuthKeyboardInteractive(ChallengeResponseProvider provider) {
        super("keyboard-interactive");
        this.provider = provider;
    }

    @Override
    public SSHPacket buildReq()
            throws UserAuthException {
        return super.buildReq() // the generic stuff
                .putString("") // lang-tag
                .putString(buildCommaSeparatedSubmethodList());
    }

    private String buildCommaSeparatedSubmethodList() {
        StringBuilder sb = new StringBuilder();
        for (String submethod : provider.getSubmethods()) {
            if (sb.length() > 0)
                sb.append(",");
            sb.append(submethod);
        }
        return sb.toString();
    }

    private static class CharArrWrap {

        private final char[] arr;

        private CharArrWrap(char[] arr) {
            this.arr = arr;
        }
    }

    @Override
    public void handle(Message cmd, SSHPacket buf)
            throws UserAuthException, TransportException {
        if (cmd != Message.USERAUTH_60) {
            super.handle(cmd, buf);
        } else {
            final CharArrWrap[] userReplies;
            try {
                provider.init(makeAccountResource(), buf.readString(), buf.readString());
                buf.readString(); // lang-tag
                final int numPrompts = buf.readUInt32AsInt();
                userReplies = new CharArrWrap[numPrompts];
                for (int i = 0; i < numPrompts; i++) {
                    final String prompt = buf.readString();
                    final boolean echo = buf.readBoolean();
                    log.debug("Requesting response for challenge `{}`; echo={}", prompt, echo);
                    userReplies[i] = new CharArrWrap(provider.getResponse(prompt, echo));
                }
            } catch (Buffer.BufferException be) {
                throw new UserAuthException(be);
            }
            respond(userReplies);
        }
    }

    private void respond(CharArrWrap[] userReplies)
            throws TransportException {
        final SSHPacket pkt = new SSHPacket(Message.USERAUTH_INFO_RESPONSE).putUInt32(userReplies.length);
        for (final CharArrWrap response : userReplies)
            pkt.putSensitiveString(response.arr);
        params.getTransport().write(pkt);
    }

    @Override
    public boolean shouldRetry() {
        return provider.shouldRetry();
    }

}