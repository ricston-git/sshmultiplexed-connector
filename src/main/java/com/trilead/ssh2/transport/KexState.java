/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package com.trilead.ssh2.transport;


import com.trilead.ssh2.DHGexParameters;
import com.trilead.ssh2.crypto.dh.DhExchange;
import com.trilead.ssh2.crypto.dh.DhGroupExchange;
import com.trilead.ssh2.packets.PacketKexInit;

import java.math.BigInteger;

/**
 * KexState.
 * 
 * @author Christian Plattner, plattner@trilead.com
 * @version $Id: KexState.java,v 1.1 2007/10/15 12:49:57 cplattne Exp $
 */
public class KexState
{
	public PacketKexInit localKEX;
	public PacketKexInit remoteKEX;
	public NegotiatedParameters np;
	public int state = 0;

	public BigInteger K;
	public byte[] H;
	
	public byte[] hostkey;
	
	public DhExchange dhx;
	public DhGroupExchange dhgx;
	public DHGexParameters dhgexParameters;
}
