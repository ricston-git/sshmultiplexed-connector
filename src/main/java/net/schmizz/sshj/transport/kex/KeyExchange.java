/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.transport.kex;

import net.schmizz.sshj.common.Message;
import net.schmizz.sshj.common.SSHPacket;
import net.schmizz.sshj.transport.Transport;
import net.schmizz.sshj.transport.TransportException;
import net.schmizz.sshj.transport.digest.Digest;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.PublicKey;

/** Key exchange algorithm. */
public interface KeyExchange {

    /**
     * Initialize the key exchange algorithm.
     *
     * @param trans the transport
     * @param V_S   the server identification string
     * @param V_C   the client identification string
     * @param I_S   the server key init packet
     * @param I_C   the client key init packet
     *
     * @throws GeneralSecurityException
     * @throws TransportException       if there is an error sending a packet
     */
    void init(Transport trans, String V_S, String V_C, byte[] I_S, byte[] I_C)
            throws GeneralSecurityException, TransportException;

    /** @return the computed H parameter */
    byte[] getH();

    /** @return the computed K parameter */
    BigInteger getK();

    /**
     * The message digest used by this key exchange algorithm.
     *
     * @return the message digest
     */
    Digest getHash();

    /** @return the host key determined from server's response packets */
    PublicKey getHostKey();

    /**
     * Process the next packet
     *
     * @param msg    message identifier
     * @param buffer the packet
     *
     * @return a boolean indicating if the processing is complete or if more packets are to be received
     *
     * @throws GeneralSecurityException
     * @throws TransportException       if there is an error sending a packet
     */
    boolean next(Message msg, SSHPacket buffer)
            throws GeneralSecurityException, TransportException;

}
