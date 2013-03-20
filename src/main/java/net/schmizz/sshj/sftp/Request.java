/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.sftp;

public final class Request
        extends SFTPPacket<Request> {

    private final PacketType type;
    private final long reqID;

    public Request(PacketType type, long reqID) {
        super(type);
        this.type = type;
        this.reqID = reqID;
        putUInt32(reqID);
    }

    public long getRequestID() {
        return reqID;
    }

    public PacketType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Request{" + reqID + ";" + type + "}";
    }

}
