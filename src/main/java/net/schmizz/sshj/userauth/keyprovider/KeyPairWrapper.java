/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.userauth.keyprovider;

import net.schmizz.sshj.common.KeyType;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/** A {@link KeyProvider} wrapper around {@link java.security.KeyPair} */
public class KeyPairWrapper
        implements KeyProvider {

    private final KeyPair kp;
    private final KeyType type;

    public KeyPairWrapper(KeyPair kp) {
        this.kp = kp;
        type = KeyType.fromKey(kp.getPublic());
    }

    public KeyPairWrapper(PublicKey publicKey, PrivateKey privateKey) {
        this(new KeyPair(publicKey, privateKey));
    }

    @Override
    public PrivateKey getPrivate() {
        return kp.getPrivate();
    }

    @Override
    public PublicKey getPublic() {
        return kp.getPublic();
    }

    @Override
    public KeyType getType() {
        return type;
    }

}
