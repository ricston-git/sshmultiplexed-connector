/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.transport.compression;

/**
 * ZLib delayed compression.
 *
 * @see Compression#isDelayed()
 */
public class DelayedZlibCompression
        extends ZlibCompression {

    /** Named factory for the ZLib Delayed Compression. */
    public static class Factory
            implements net.schmizz.sshj.common.Factory.Named<Compression> {

        @Override
        public Compression create() {
            return new DelayedZlibCompression();
        }

        @Override
        public String getName() {
            return "zlib@openssh.com";
        }
    }

    @Override
    public boolean isDelayed() {
        return true;
    }

}
