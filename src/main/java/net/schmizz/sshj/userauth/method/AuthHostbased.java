/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.userauth.method;

import net.schmizz.sshj.common.SSHPacket;
import net.schmizz.sshj.userauth.UserAuthException;
import net.schmizz.sshj.userauth.keyprovider.KeyProvider;

/** Implements the {@code hostbased} SSH authentication method. */
public class AuthHostbased
        extends KeyedAuthMethod {

    protected final String hostname;
    protected final String hostuser;

    public AuthHostbased(KeyProvider kProv, String hostname, String hostuser) {
        super("hostbased", kProv);
        this.hostname = hostname;
        this.hostuser = hostuser;
    }

    @Override
    protected SSHPacket buildReq()
            throws UserAuthException {
        final SSHPacket req = putPubKey(super.buildReq());
        req.putString(hostname).putString(hostuser);
        return putSig(req);
    }

}