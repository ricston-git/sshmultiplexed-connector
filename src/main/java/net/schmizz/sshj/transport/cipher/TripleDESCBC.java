/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.transport.cipher;

/** {@code 3des-cbc} cipher */
public class TripleDESCBC
        extends BaseCipher {

    /** Named factory for TripleDESCBC Cipher */
    public static class Factory
            implements net.schmizz.sshj.common.Factory.Named<Cipher> {

        @Override
        public Cipher create() {
            return new TripleDESCBC();
        }

        @Override
        public String getName() {
            return "3des-cbc";
        }
    }

    public TripleDESCBC() {
        super(8, 24, "DESede", "DESede/CBC/NoPadding");
    }

}
