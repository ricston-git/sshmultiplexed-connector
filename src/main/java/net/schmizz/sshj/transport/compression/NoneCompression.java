/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.transport.compression;

/** No-op <code>Compression</code>. */
public abstract class NoneCompression
        implements Compression {

    /** Named factory for the no-op <code>Compression</code> */
    public static class Factory
            implements net.schmizz.sshj.common.Factory.Named<Compression> {

        @Override
        public Compression create() {
            return null;
        }

        @Override
        public String getName() {
            return "none";
        }
    }

}
