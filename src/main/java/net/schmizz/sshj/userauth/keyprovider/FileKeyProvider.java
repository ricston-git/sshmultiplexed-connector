/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.userauth.keyprovider;

import net.schmizz.sshj.userauth.password.PasswordFinder;

import java.io.File;

/** A file key provider is initialized with a location of */
public interface FileKeyProvider
        extends KeyProvider {

    enum Format {
        PKCS8,
        OpenSSH,
        Unknown
    }

    void init(File location);

    void init(File location, PasswordFinder pwdf);

    void init(String privateKey, String publicKey);

    void init(String privateKey, String publicKey, PasswordFinder pwdf);
}
