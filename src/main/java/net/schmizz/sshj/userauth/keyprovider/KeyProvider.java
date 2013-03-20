/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.userauth.keyprovider;

import net.schmizz.sshj.common.KeyType;

import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;

/** A KeyProvider is a container for a public-private keypair. */
public interface KeyProvider {

    /**
     * @return the private key.
     *
     * @throws IOException if there is an I/O error retrieving the private key
     */
    PrivateKey getPrivate()
            throws IOException;

    /**
     * @return the public key.
     *
     * @throws IOException if there is an I/O error retrieving the public key
     */
    PublicKey getPublic()
            throws IOException;

    /**
     * @return the {@link KeyType}.
     *
     * @throws IOException if there is an I/O error retrieving the key type
     */
    KeyType getType()
            throws IOException;

}
