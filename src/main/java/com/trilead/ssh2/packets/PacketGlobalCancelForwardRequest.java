/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */


package com.trilead.ssh2.packets;

/**
 * PacketGlobalCancelForwardRequest.
 * 
 * @author Christian Plattner, plattner@trilead.com
 * @version $Id: PacketGlobalCancelForwardRequest.java,v 1.1 2007/10/15 12:49:55
 *          cplattne Exp $
 */
public class PacketGlobalCancelForwardRequest
{
	byte[] payload;

	public boolean wantReply;
	public String bindAddress;
	public int bindPort;

	public PacketGlobalCancelForwardRequest(boolean wantReply, String bindAddress, int bindPort)
	{
		this.wantReply = wantReply;
		this.bindAddress = bindAddress;
		this.bindPort = bindPort;
	}

	public byte[] getPayload()
	{
		if (payload == null)
		{
			TypesWriter tw = new TypesWriter();
			tw.writeByte(Packets.SSH_MSG_GLOBAL_REQUEST);

			tw.writeString("cancel-tcpip-forward");
			tw.writeBoolean(wantReply);
			tw.writeString(bindAddress);
			tw.writeUINT32(bindPort);

			payload = tw.getBytes();
		}
		return payload;
	}
}
