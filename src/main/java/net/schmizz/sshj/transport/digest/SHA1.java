/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.transport.digest;

/** SHA1 Digest. */
public class SHA1
        extends BaseDigest {

    /** Named factory for SHA1 digest */
    public static class Factory
            implements net.schmizz.sshj.common.Factory.Named<Digest> {

        @Override
        public Digest create() {
            return new SHA1();
        }

        @Override
        public String getName() {
            return "sha1";
        }
    }

    /** Create a new instance of a SHA1 digest */
    public SHA1() {
        super("SHA-1", 20);
    }
}
