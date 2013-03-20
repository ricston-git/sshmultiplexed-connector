/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.transport.verification;

import java.security.PublicKey;

public final class PromiscuousVerifier
        implements HostKeyVerifier {

    @Override
    public boolean verify(String hostname, int port, PublicKey key) {
        return true;
    }

}
