/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.sftp;

import net.schmizz.sshj.common.Buffer;

public class SFTPPacket<T extends SFTPPacket<T>>
        extends Buffer<T> {

    public SFTPPacket() {
        super();
    }

    public SFTPPacket(Buffer<T> buf) {
        super(buf);
    }

    public SFTPPacket(PacketType pt) {
        super();
        putByte(pt.toByte());
    }

    public FileAttributes readFileAttributes()
            throws SFTPException {
        final FileAttributes.Builder builder = new FileAttributes.Builder();
        try {
            final int mask = readUInt32AsInt();
            if (FileAttributes.Flag.SIZE.isSet(mask))
                builder.withSize(readUInt64());
            if (FileAttributes.Flag.UIDGID.isSet(mask))
                builder.withUIDGID(readUInt32AsInt(), readUInt32AsInt());
            if (FileAttributes.Flag.MODE.isSet(mask))
                builder.withPermissions(readUInt32AsInt());
            if (FileAttributes.Flag.ACMODTIME.isSet(mask))
                builder.withAtimeMtime(readUInt32AsInt(), readUInt32AsInt());
            if (FileAttributes.Flag.EXTENDED.isSet(mask)) {
                final int extCount = readUInt32AsInt();
                for (int i = 0; i < extCount; i++)
                    builder.withExtended(readString(), readString());
            }
        } catch (BufferException be) {
            throw new SFTPException(be);
        }
        return builder.build();
    }

    public PacketType readType()
            throws SFTPException {
        try {
            return PacketType.fromByte(readByte());
        } catch (BufferException be) {
            throw new SFTPException(be);
        }
    }

    public T putFileAttributes(FileAttributes fa) {
        return putRawBytes(fa.toBytes());
    }

    public T putType(PacketType type) {
        return putByte(type.toByte());
    }

}
