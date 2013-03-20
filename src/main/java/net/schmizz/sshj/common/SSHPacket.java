/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.common;

import java.util.Arrays;

public final class SSHPacket
        extends Buffer<SSHPacket> {

    public SSHPacket() {
        super();
    }

    public SSHPacket(int size) {
        super(size);
    }

    public SSHPacket(byte[] data) {
        super(data);
    }

    /**
     * Constructs new buffer for the specified SSH packet and reserves the needed space (5 bytes) for the packet
     * header.
     *
     * @param msg the SSH command
     */
    public SSHPacket(Message msg) {
        super();
        rpos = wpos = 5;
        putMessageID(msg);
    }

    public SSHPacket(SSHPacket p) {
        this.data = Arrays.copyOf(p.data, p.wpos);
        this.rpos = p.rpos;
        this.wpos = p.wpos;
    }

    /**
     * Reads an SSH byte and returns it as {@link Message}
     *
     * @return the message identifier
     */
    public Message readMessageID()
            throws BufferException {
        return Message.fromByte(readByte());
    }

    /**
     * Writes a byte indicating the SSH message identifier
     *
     * @param msg the identifier as a {@link Message} type
     *
     * @return this
     */
    public SSHPacket putMessageID(Message msg) {
        return putByte(msg.toByte());
    }

}