/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.transport.kex;

import java.security.GeneralSecurityException;

/**
 * Diffie-Hellman key exchange with SHA-1 and Oakley Group 2 [RFC2409] (1024-bit MODP Group).
 *
 * @see <a href="http://www.ietf.org/rfc/rfc4253.txt">RFC 4253</a>
 */
public class DHG1
        extends AbstractDHG {

    /** Named factory for DHG1 key exchange */
    public static class Factory
            implements net.schmizz.sshj.common.Factory.Named<KeyExchange> {

        @Override
        public KeyExchange create() {
            return new DHG1();
        }

        @Override
        public String getName() {
            return "diffie-hellman-group1-sha1";
        }

    }

    @Override
    protected void initDH(DH dh)
            throws GeneralSecurityException {
        dh.init(DHGroupData.P1, DHGroupData.G);
    }

}
