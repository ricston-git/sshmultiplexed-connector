/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.transport.compression;

import net.schmizz.sshj.common.Buffer;
import net.schmizz.sshj.transport.TransportException;

/** Interface used to compress the stream of data between the SSH server and clients. */
public interface Compression {

    /** Enum identifying if this object will be used to compress or uncompress data. */
    enum Mode {
        INFLATE,
        DEFLATE
    }

    /**
     * Initialize this object to either compress or uncompress data. This method must be called prior to any calls to
     * either <code>compress</code> or <code>uncompress</code>. Once the object has been initialized, only one of
     * <code>compress</code> or <code>uncompress</code> method can be called.
     *
     * @param mode
     */
    void init(Mode mode);

    /**
     * Delayed compression is an Open-SSH specific feature which informs both the client and server to not compress data
     * before the session has been authenticated.
     *
     * @return if the compression is delayed after authentication or not
     */
    boolean isDelayed();

    /**
     * Compress the given buffer in place.
     *
     * @param buffer the buffer containing the data to compress s
     */
    void compress(Buffer buffer);

    /**
     * Uncompress the data in a buffer into another buffer.
     *
     * @param from the buffer containing the data to uncompress
     * @param to   the buffer receiving the uncompressed data
     *
     * @throws TransportException
     */
    void uncompress(Buffer from, Buffer to)
            throws TransportException;

}
