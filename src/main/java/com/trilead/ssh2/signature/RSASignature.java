/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */


package com.trilead.ssh2.signature;

import java.math.BigInteger;


/**
 * RSASignature.
 * 
 * @author Christian Plattner, plattner@trilead.com
 * @version $Id: RSASignature.java,v 1.1 2007/10/15 12:49:57 cplattne Exp $
 */

public class RSASignature
{
	BigInteger s;

	public BigInteger getS()
	{
		return s;
	}

	public RSASignature(BigInteger s)
	{
		this.s = s;
	}
}