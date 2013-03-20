/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.transport.cipher;

/** {@code blowfish-ctr} cipher */
public class BlowfishCBC
        extends BaseCipher {

    /** Named factory for BlowfishCBC Cipher */
    public static class Factory
            implements net.schmizz.sshj.common.Factory.Named<Cipher> {

        @Override
        public Cipher create() {
            return new BlowfishCBC();
        }

        @Override
        public String getName() {
            return "blowfish-cbc";
        }
    }

    public BlowfishCBC() {
        super(8, 16, "Blowfish", "Blowfish/CBC/NoPadding");
    }

}
