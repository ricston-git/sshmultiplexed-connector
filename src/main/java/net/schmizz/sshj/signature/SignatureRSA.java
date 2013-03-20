/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.signature;

import net.schmizz.sshj.common.KeyType;
import net.schmizz.sshj.common.SSHRuntimeException;

import java.security.SignatureException;

/** RSA {@link Signature} */
public class SignatureRSA
        extends AbstractSignature {

    /** A named factory for RSA {@link Signature} */
    public static class Factory
            implements net.schmizz.sshj.common.Factory.Named<Signature> {

        @Override
        public Signature create() {
            return new SignatureRSA();
        }

        @Override
        public String getName() {
            return KeyType.RSA.toString();
        }

    }

    public SignatureRSA() {
        super("SHA1withRSA");
    }

    @Override
    public byte[] sign() {
        try {
            return signature.sign();
        } catch (SignatureException e) {
            throw new SSHRuntimeException(e);
        }
    }

    @Override
    public boolean verify(byte[] sig) {
        sig = extractSig(sig);
        try {
            return signature.verify(sig);
        } catch (SignatureException e) {
            throw new SSHRuntimeException(e);
        }
    }

}
