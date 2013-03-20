/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.userauth.method;

import net.schmizz.sshj.common.Buffer;
import net.schmizz.sshj.common.Factory;
import net.schmizz.sshj.common.KeyType;
import net.schmizz.sshj.common.SSHPacket;
import net.schmizz.sshj.signature.Signature;
import net.schmizz.sshj.userauth.UserAuthException;
import net.schmizz.sshj.userauth.keyprovider.KeyProvider;

import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;

public abstract class KeyedAuthMethod
        extends AbstractAuthMethod {

    protected final KeyProvider kProv;

    public KeyedAuthMethod(String name, KeyProvider kProv) {
        super(name);
        this.kProv = kProv;
    }

    protected SSHPacket putPubKey(SSHPacket reqBuf)
            throws UserAuthException {
        PublicKey key;
        try {
            key = kProv.getPublic();
        } catch (IOException ioe) {
            throw new UserAuthException("Problem getting public key from " + kProv, ioe);
        }

        // public key as 2 strings: [ key type | key blob ]
        reqBuf.putString(KeyType.fromKey(key).toString())
              .putString(new Buffer.PlainBuffer().putPublicKey(key).getCompactData());
        return reqBuf;
    }

    protected SSHPacket putSig(SSHPacket reqBuf)
            throws UserAuthException {
        PrivateKey key;
        try {
            key = kProv.getPrivate();
        } catch (IOException ioe) {
            throw new UserAuthException("Problem getting private key from " + kProv, ioe);
        }

        final String kt = KeyType.fromKey(key).toString();
        Signature sigger = Factory.Named.Util.create(params.getTransport().getConfig().getSignatureFactories(), kt);
        if (sigger == null)
            throw new UserAuthException("Could not create signature instance for " + kt + " key");

        sigger.init(null, key);
        sigger.update(new Buffer.PlainBuffer()
                              .putString(params.getTransport().getSessionID())
                              .putBuffer(reqBuf) // & rest of the data for sig
                              .getCompactData());
        reqBuf.putSignature(kt, sigger.sign());
        return reqBuf;
    }

}
