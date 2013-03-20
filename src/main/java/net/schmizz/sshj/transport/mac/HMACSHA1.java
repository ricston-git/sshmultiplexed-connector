/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.transport.mac;

/** HMAC-SHA1 <code>MAC</code> */
public class HMACSHA1
        extends BaseMAC {

    /** Named factory for the HMAC-SHA1 <code>MAC</code> */
    public static class Factory
            implements net.schmizz.sshj.common.Factory.Named<MAC> {

        @Override
        public MAC create() {
            return new HMACSHA1();
        }

        @Override
        public String getName() {
            return "hmac-sha1";
        }
    }

    public HMACSHA1() {
        super("HmacSHA1", 20, 20);
    }
}
