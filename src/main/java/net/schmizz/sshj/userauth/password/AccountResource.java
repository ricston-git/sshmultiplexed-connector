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

public class AccountResource
        extends Resource<String> {

    public AccountResource(String user, String host) {
        super(user + "@" + host);
    }

    @Override
    public Reader getReader()
            throws IOException {
        return new StringReader(getDetail());
    }
}
