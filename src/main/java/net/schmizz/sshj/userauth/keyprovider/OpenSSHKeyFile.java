/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.userauth.keyprovider;

import net.schmizz.sshj.common.Base64;
import net.schmizz.sshj.common.Buffer;
import net.schmizz.sshj.common.KeyType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.security.PublicKey;


/**
 * Represents an OpenSSH identity that consists of a PKCS8-encoded private key file and an unencrypted public key file
 * of the same name with the {@code ".pub"} extension. This allows to delay requesting of the passphrase until the
 * private key is requested.
 *
 * @see PKCS8KeyFile
 */
public class OpenSSHKeyFile
        extends PKCS8KeyFile {

    public static class Factory
            implements net.schmizz.sshj.common.Factory.Named<FileKeyProvider> {

        @Override
        public FileKeyProvider create() {
            return new OpenSSHKeyFile();
        }

        @Override
        public String getName() {
            return "OpenSSH";
        }
    }

    private PublicKey pubKey;

    @Override
    public PublicKey getPublic()
            throws IOException {
        return pubKey != null ? pubKey : super.getPublic();
    }

    @Override
    public void init(File location) {
        final File f = new File(location + ".pub");
        if (f.exists())
            try {
                initPubKey(new FileReader(f));
            } catch (IOException e) {
                // let super provide both public & private key
                log.warn("Error reading public key file: {}", e.toString());
            }
        super.init(location);
    }

    @Override
    public void init(String privateKey, String publicKey) {
        if (publicKey != null) {
            initPubKey(new StringReader(publicKey));
        }
        super.init(privateKey, null);
    }

    /**
     * Read and store the separate public key provided alongside the private key
     *
     * @param publicKey Public key accessible through a {@code Reader}
     */
    private void initPubKey(Reader publicKey) {
        try {
            final BufferedReader br = new BufferedReader(publicKey);
            try {
                final String keydata = br.readLine();
                if (keydata != null) {
                    String[] parts = keydata.split(" ");
                    assert parts.length >= 2;
                    type = KeyType.fromString(parts[0]);
                    pubKey = new Buffer.PlainBuffer(Base64.decode(parts[1])).readPublicKey();
                }
            } finally {
                br.close();
            }
        } catch (IOException e) {
            // let super provide both public & private key
            log.warn("Error reading public key: {}", e.toString());
        }
    }
}
