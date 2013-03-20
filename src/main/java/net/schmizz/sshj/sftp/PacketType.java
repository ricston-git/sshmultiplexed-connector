/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.sftp;

public enum PacketType {

    UNKNOWN(0),
    INIT(1),
    VERSION(2),
    OPEN(3),
    CLOSE(4),
    READ(5),
    WRITE(6),
    LSTAT(7),
    FSTAT(8),
    SETSTAT(9),
    FSETSTAT(10),
    OPENDIR(11),
    READDIR(12),
    REMOVE(13),
    MKDIR(14),
    RMDIR(15),
    REALPATH(16),
    STAT(17),
    RENAME(18),
    READLINK(19),
    SYMLINK(20),
    STATUS(101),
    HANDLE(102),
    DATA(103),
    NAME(104),
    ATTRS(105),
    EXTENDED(200),
    EXTENDED_REPLY(201);

    private final byte b;

    private static final PacketType[] cache = new PacketType[256];

    static {
        for (PacketType t : PacketType.values())
            cache[t.toByte() & 0xff] = t;
    }

    private PacketType(int b) {
        this.b = (byte) b;
    }

    public static PacketType fromByte(byte b) {
        return cache[b & 0xff];
    }

    public byte toByte() {
        return b;
    }

}
