/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.transport.random;

import net.schmizz.sshj.common.Factory;

/** A random factory wrapper that uses a single random instance. The underlying random instance has to be thread safe. */
public class SingletonRandomFactory
        implements Random, Factory<Random> {

    private final Random random;

    public SingletonRandomFactory(Factory<Random> factory) {
        random = factory.create();
    }

    @Override
    public Random create() {
        return this;
    }

    @Override
    public void fill(byte[] bytes, int start, int len) {
        random.fill(bytes, start, len);
    }

}
