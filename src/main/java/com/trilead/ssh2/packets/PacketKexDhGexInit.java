/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package com.trilead.ssh2.packets;

import java.math.BigInteger;

/**
 * PacketKexDhGexInit.
 * 
 * @author Christian Plattner, plattner@trilead.com
 * @version $Id: PacketKexDhGexInit.java,v 1.1 2007/10/15 12:49:55 cplattne Exp $
 */
public class PacketKexDhGexInit
{
	byte[] payload;

	BigInteger e;

	public PacketKexDhGexInit(BigInteger e)
	{
		this.e = e;
	}

	public byte[] getPayload()
	{
		if (payload == null)
		{
			TypesWriter tw = new TypesWriter();
			tw.writeByte(Packets.SSH_MSG_KEX_DH_GEX_INIT);
			tw.writeMPInt(e);
			payload = tw.getBytes();
		}
		return payload;
	}
}
