/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.transport.random;

import org.bouncycastle.crypto.prng.RandomGenerator;
import org.bouncycastle.crypto.prng.VMPCRandomGenerator;

import java.security.SecureRandom;

/**
 * BouncyCastle <code>Random</code>. This pseudo random number generator uses the a very fast PRNG from BouncyCastle.
 * The JRE random will be used when creating a new generator to add some random data to the seed.
 */
public class BouncyCastleRandom
        implements Random {

    /** Named factory for the BouncyCastle <code>Random</code> */
    public static class Factory
            implements net.schmizz.sshj.common.Factory<Random> {

        @Override
        public Random create() {
            return new BouncyCastleRandom();
        }

    }

    private final RandomGenerator random = new VMPCRandomGenerator();

    public BouncyCastleRandom() {
        byte[] seed = new SecureRandom().generateSeed(8);
        random.addSeedMaterial(seed);
    }

    @Override
    public void fill(byte[] bytes, int start, int len) {
        random.nextBytes(bytes, start, len);
    }

}
