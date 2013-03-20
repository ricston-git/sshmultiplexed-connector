/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.userauth.password;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class PrivateKeyStringResource
        extends Resource<String> {

    public PrivateKeyStringResource(String string) {
        super(string);
    }

    @Override
    public Reader getReader()
            throws IOException {
        return new StringReader(getDetail());
    }

    @Override
    public String toString() {
        // If not overridden, the superclass's will return the private key as
        // part of the string.
        return "[" + getClass().getSimpleName() + "]";
    }
}
