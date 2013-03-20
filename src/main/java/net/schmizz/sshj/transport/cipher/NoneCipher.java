/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.transport.cipher;

/** Represents a no-op cipher. */
public class NoneCipher
        implements Cipher {

    /** Named factory for the no-op Cipher */
    public static class Factory
            implements net.schmizz.sshj.common.Factory.Named<Cipher> {

        @Override
        public Cipher create() {
            return new NoneCipher();
        }

        @Override
        public String getName() {
            return "none";
        }
    }

    @Override
    public int getBlockSize() {
        return 8;
    }

    @Override
    public int getIVSize() {
        return 8;
    }

    @Override
    public void init(Mode mode, byte[] bytes, byte[] bytes1) {
    }

    @Override
    public void update(byte[] input, int inputOffset, int inputLen) {
    }

}
