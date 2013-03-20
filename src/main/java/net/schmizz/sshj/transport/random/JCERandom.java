/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.transport.random;

import java.security.SecureRandom;

/** A {@link Random} implementation using the built-in {@link SecureRandom} PRNG. */
public class JCERandom
        implements Random {

    /** Named factory for the JCE {@link Random} */
    public static class Factory
            implements net.schmizz.sshj.common.Factory.Named<Random> {

        @Override
        public Random create() {
            return new JCERandom();
        }

        @Override
        public String getName() {
            return "default";
        }

    }

    private byte[] tmp = new byte[16];
    private final SecureRandom random = new SecureRandom();

    /**
     * Fill the given byte-array with random bytes from this PRNG.
     *
     * @param foo   the byte-array
     * @param start the offset to start at
     * @param len   the number of bytes to fill
     */
    @Override
    public synchronized void fill(byte[] foo, int start, int len) {
        if (start == 0 && len == foo.length)
            random.nextBytes(foo);
        else
            synchronized (this) {
                if (len > tmp.length)
                    tmp = new byte[len];
                random.nextBytes(tmp);
                System.arraycopy(tmp, 0, foo, start, len);
            }
    }

}
