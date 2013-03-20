/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.userauth.keyprovider;

import net.schmizz.sshj.common.IOUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class KeyProviderUtil {

    /**
     * Attempts to detect how a key file is encoded.
     * <p/>
     * Return values are consistent with the {@code NamedFactory} implementations in the {@code keyprovider} package.
     *
     * @param location
     *
     * @return name of the key file format
     *
     * @throws java.io.IOException
     */
    public static FileKeyProvider.Format detectKeyFileFormat(File location)
            throws IOException {
        return detectKeyFileFormat(new FileReader(location),
                                   new File(location + ".pub").exists());
    }

    /**
     * Attempts to detect how a key file is encoded.
     * <p/>
     * Return values are consistent with the {@code NamedFactory} implementations in the {@code keyprovider} package.
     *
     * @param privateKey     Private key stored in a string
     * @param separatePubKey Is the public key stored separately from the private key
     *
     * @return name of the key file format
     *
     * @throws java.io.IOException
     */
    public static FileKeyProvider.Format detectKeyFileFormat(String privateKey,
                                                             boolean separatePubKey)
            throws IOException {
        return detectKeyFileFormat(new StringReader(privateKey), separatePubKey);
    }

    /**
     * Attempts to detect how a key file is encoded.
     * <p/>
     * Return values are consistent with the {@code NamedFactory} implementations in the {@code keyprovider} package.
     *
     * @param privateKey     Private key accessible through a {@code Reader}
     * @param separatePubKey Is the public key stored separately from the private key
     *
     * @return name of the key file format
     *
     * @throws java.io.IOException
     */
    private static FileKeyProvider.Format detectKeyFileFormat(Reader privateKey,
                                                              boolean separatePubKey)
            throws IOException {
        BufferedReader br = new BufferedReader(privateKey);
        String firstLine = br.readLine();
        IOUtils.closeQuietly(br);
        if (firstLine == null)
            throw new IOException("Empty file");
        if (firstLine.startsWith("-----BEGIN") && firstLine.endsWith("PRIVATE KEY-----"))
            if (separatePubKey)
                // Can delay asking for password since have unencrypted pubkey
                return FileKeyProvider.Format.OpenSSH;
            else
                // More general
                return FileKeyProvider.Format.PKCS8;
        /*
         * TODO: Tectia, PuTTY (.ppk) ...
         */
        return FileKeyProvider.Format.Unknown;
    }
}
