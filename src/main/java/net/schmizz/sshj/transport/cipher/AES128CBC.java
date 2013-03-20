/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.transport.cipher;

/** {@code aes128-cbc} cipher */
public class AES128CBC
        extends BaseCipher {

    /** Named factory for AES128CBC Cipher */
    public static class Factory
            implements net.schmizz.sshj.common.Factory.Named<Cipher> {

        @Override
        public Cipher create() {
            return new AES128CBC();
        }

        @Override
        public String getName() {
            return "aes128-cbc";
        }
    }

    public AES128CBC() {
        super(16, 16, "AES", "AES/CBC/NoPadding");
    }

}
