/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.userauth.password;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class PrivateKeyFileResource
        extends Resource<File> {

    public PrivateKeyFileResource(File privateKeyFile) {
        super(privateKeyFile);
    }

    @Override
    public Reader getReader()
            throws IOException {
        return new InputStreamReader(new FileInputStream(getDetail()));
    }
}
