/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package com.trilead.ssh2.packets;

import com.trilead.ssh2.DHGexParameters;

/**
 * PacketKexDhGexRequest.
 * 
 * @author Christian Plattner, plattner@trilead.com
 * @version $Id: PacketKexDhGexRequest.java,v 1.1 2007/10/15 12:49:55 cplattne Exp $
 */
public class PacketKexDhGexRequest
{
	byte[] payload;

	int min;
	int n;
	int max;

	public PacketKexDhGexRequest(DHGexParameters para)
	{
		this.min = para.getMin_group_len();
		this.n = para.getPref_group_len();
		this.max = para.getMax_group_len();
	}

	public byte[] getPayload()
	{
		if (payload == null)
		{
			TypesWriter tw = new TypesWriter();
			tw.writeByte(Packets.SSH_MSG_KEX_DH_GEX_REQUEST);
			tw.writeUINT32(min);
			tw.writeUINT32(n);
			tw.writeUINT32(max);
			payload = tw.getBytes();
		}
		return payload;
	}
}
