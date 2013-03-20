/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.transport.random;

/** A pseudo random number generator. */
public interface Random {

    /**
     * Fill part of bytes with random values.
     *
     * @param bytes byte array to be filled.
     * @param start index to start filling at.
     * @param len   length of segment to fill.
     */
    void fill(byte[] bytes, int start, int len);

}
