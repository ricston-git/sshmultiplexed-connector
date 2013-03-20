/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.sftp;

import net.schmizz.sshj.common.Buffer;

public final class Response
        extends SFTPPacket<Response> {

    public static enum StatusCode {
        UNKNOWN(-1),
        OK(0),
        EOF(1),
        NO_SUCH_FILE(2),
        PERMISSION_DENIED(3),
        FAILURE(4),
        BAD_MESSAGE(5),
        NO_CONNECTION(6),
        CONNECITON_LOST(7),
        OP_UNSUPPORTED(8);

        private final int code;

        public static StatusCode fromInt(int code) {
            for (StatusCode s : StatusCode.values())
                if (s.code == code)
                    return s;
            return UNKNOWN;
        }

        private StatusCode(int code) {
            this.code = code;
        }

    }

    private final int protocolVersion;
    private final PacketType type;
    private final long reqID;

    public Response(Buffer<Response> pk, int protocolVersion)
            throws SFTPException {
        super(pk);
        this.protocolVersion = protocolVersion;
        this.type = readType();
        try {
            this.reqID = readUInt32();
        } catch (BufferException be) {
            throw new SFTPException(be);
        }
    }

    public int getProtocolVersion() {
        return protocolVersion;
    }

    public long getRequestID() {
        return reqID;
    }

    public PacketType getType() {
        return type;
    }

    public StatusCode readStatusCode()
            throws SFTPException {
        try {
            return StatusCode.fromInt(readUInt32AsInt());
        } catch (BufferException be) {
            throw new SFTPException(be);
        }
    }

    public Response ensurePacketTypeIs(PacketType pt)
            throws SFTPException {
        if (getType() != pt)
            if (getType() == PacketType.STATUS)
                error(readStatusCode());
            else
                throw new SFTPException("Unexpected packet " + getType());
        return this;
    }

    public Response ensureStatusPacketIsOK()
            throws SFTPException {
        return ensurePacketTypeIs(PacketType.STATUS).ensureStatusIs(StatusCode.OK);
    }

    public Response ensureStatusIs(StatusCode acceptable)
            throws SFTPException {
        final StatusCode sc = readStatusCode();
        if (sc != acceptable)
            error(sc);
        return this;
    }

    protected String error(StatusCode sc)
            throws SFTPException {
        try {
            throw new SFTPException(sc, protocolVersion < 3 ? sc.toString() : readString());
        } catch (BufferException be) {
            throw new SFTPException(be);
        }
    }

}
