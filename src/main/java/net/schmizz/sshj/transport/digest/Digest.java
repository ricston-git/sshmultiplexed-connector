/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.transport.digest;

/** Interface used to compute digests, based on algorithms such as MD5 or SHA1. */
public interface Digest {

    byte[] digest();

    int getBlockSize();

    void init();

    void update(byte[] foo);

    void update(byte[] foo, int start, int len);

}
