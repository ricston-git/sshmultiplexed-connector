/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.transport.cipher;

/** {@code aes192-ctr} cipher */
public class AES192CTR
        extends BaseCipher {

    /** Named factory for AES192CTR Cipher */
    public static class Factory
            implements net.schmizz.sshj.common.Factory.Named<Cipher> {

        @Override
        public Cipher create() {
            return new AES192CTR();
        }

        @Override
        public String getName() {
            return "aes192-ctr";
        }
    }

    public AES192CTR() {
        super(16, 24, "AES", "AES/CTR/NoPadding");
    }

}
