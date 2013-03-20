/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.transport.kex;

import net.schmizz.sshj.common.SSHRuntimeException;
import net.schmizz.sshj.common.SecurityUtils;

import javax.crypto.KeyAgreement;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.DHPublicKeySpec;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;

/** Diffie-Hellman key generator. */
public class DH {

    private BigInteger p;
    private BigInteger g;
    private BigInteger e; // my public key
    private BigInteger K; // shared secret key
    private final KeyPairGenerator generator;
    private final KeyAgreement agreement;

    public DH() {
        try {
            generator = SecurityUtils.getKeyPairGenerator("DH");
            agreement = SecurityUtils.getKeyAgreement("DH");
        } catch (GeneralSecurityException e) {
            throw new SSHRuntimeException(e);
        }
    }

    public void init(BigInteger p, BigInteger g)
            throws GeneralSecurityException {
        this.p = p;
        this.g = g;
        generator.initialize(new DHParameterSpec(p, g));
        final KeyPair kp = generator.generateKeyPair();
        agreement.init(kp.getPrivate());
        e = ((javax.crypto.interfaces.DHPublicKey) kp.getPublic()).getY();
    }

    public void computeK(BigInteger f)
            throws GeneralSecurityException {
        final KeyFactory keyFactory = SecurityUtils.getKeyFactory("DH");
        final PublicKey yourPubKey = keyFactory.generatePublic(new DHPublicKeySpec(f, p, g));
        agreement.doPhase(yourPubKey, true);
        K = new BigInteger(1, agreement.generateSecret());
    }

    public BigInteger getE() {
        return e;
    }

    public BigInteger getK() {
        return K;
    }

}
