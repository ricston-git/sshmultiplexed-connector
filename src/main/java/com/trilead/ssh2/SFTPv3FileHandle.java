/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */


package com.trilead.ssh2;

/**
 * A <code>SFTPv3FileHandle</code>.
 * 
 * @author Christian Plattner, plattner@trilead.com
 * @version $Id: SFTPv3FileHandle.java,v 1.1 2007/10/15 12:49:56 cplattne Exp $
 */

public class SFTPv3FileHandle
{
	final SFTPv3Client client;
	final byte[] fileHandle;
	boolean isClosed = false;

	/* The constructor is NOT public */

	SFTPv3FileHandle(SFTPv3Client client, byte[] h)
	{
		this.client = client;
		this.fileHandle = h;
	}

	/**
	 * Get the SFTPv3Client instance which created this handle. 
	 * 
	 * @return A SFTPv3Client instance.
	 */
	public SFTPv3Client getClient()
	{
		return client;
	}

	/**
	 * Check if this handle was closed with the {@link SFTPv3Client#closeFile(SFTPv3FileHandle)} method
	 * of the <code>SFTPv3Client</code> instance which created the handle.
	 * 
	 * @return if the handle is closed.
	 */
	public boolean isClosed()
	{
		return isClosed;
	}
}
