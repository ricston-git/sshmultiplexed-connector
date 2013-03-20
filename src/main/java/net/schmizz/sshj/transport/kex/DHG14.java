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
 * Diffie-Hellman key exchange with SHA-1 and Oakley Group 14 [RFC3526] (2048-bit MODP Group).
 * <p/>
 * DHG14 does not work with the default JCE implementation provided by Sun because it does not support 2048 bits
 * encryption. It requires BouncyCastle to be used.
 */
public class DHG14
        extends AbstractDHG {

    /** Named factory for DHG14 key exchange */
    public static class Factory
            implements net.schmizz.sshj.common.Factory.Named<KeyExchange> {

        @Override
        public KeyExchange create() {
            return new DHG14();
        }

        @Override
        public String getName() {
            return "diffie-hellman-group14-sha1";
        }

    }

    @Override
    protected void initDH(DH dh)
            throws GeneralSecurityException {
        dh.init(DHGroupData.P14, DHGroupData.G);
    }

}
