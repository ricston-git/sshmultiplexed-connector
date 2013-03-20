/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.transport.mac;

/** HMAC-MD5 <code>MAC</code>. */
public class HMACMD5
        extends BaseMAC {

    /** Named factory for the HMACMD5 <code>MAC</code> */
    public static class Factory
            implements net.schmizz.sshj.common.Factory.Named<MAC> {

        @Override
        public MAC create() {
            return new HMACMD5();
        }

        @Override
        public String getName() {
            return "hmac-md5";
        }
    }

    public HMACMD5() {
        super("HmacMD5", 16, 16);
    }
}
