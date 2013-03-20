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
import net.schmizz.sshj.userauth.keyprovider.KeyProvider;

/**
 * Implements the {@code "publickey"} SSH authentication method.
 * <p/>
 * Requesteing authentication with this method first sends a "feeler" request with just the public key, and if the
 * server responds with {@code SSH_MSG_USERAUTH_PK_OK} indicating that the key is acceptable, it proceeds to send a
 * request signed with the private key. Therefore, private keys are not requested from the associated {@link
 * KeyProvider} unless needed.
 */
public class AuthPublickey
        extends KeyedAuthMethod {

    /** Initialize this method with the provider for public and private key. */
    public AuthPublickey(KeyProvider kProv) {
        super("publickey", kProv);
    }

    /** Internal use. */
    @Override
    public void handle(Message cmd, SSHPacket buf)
            throws UserAuthException, TransportException {
        if (cmd == Message.USERAUTH_60)
            sendSignedReq();
        else
            super.handle(cmd, buf);
    }

    /**
     * Builds SSH_MSG_USERAUTH_REQUEST packet.
     *
     * @param signed whether the request packet will contain signature
     *
     * @return the {@link SSHPacket} containing the request packet
     *
     * @throws UserAuthException
     */
    private SSHPacket buildReq(boolean signed)
            throws UserAuthException {
        log.debug("Attempting authentication using {}", kProv);
        return putPubKey(super.buildReq().putBoolean(signed));
    }

    /**
     * Send SSH_MSG_USERAUTH_REQUEST containing the signature.
     *
     * @throws UserAuthException
     * @throws TransportException
     */
    private void sendSignedReq()
            throws UserAuthException, TransportException {
        log.debug("Key acceptable, sending signed request");
        params.getTransport().write(putSig(buildReq(true)));
    }

    /** Builds a feeler request (sans signature). */
    @Override
    protected SSHPacket buildReq()
            throws UserAuthException {
        return buildReq(false);
    }

}
