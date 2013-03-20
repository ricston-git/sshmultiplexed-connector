/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.connection.channel.direct;

import net.schmizz.sshj.common.SSHException;

/** A factory interface for creating SSH {@link Session session channels}. */
public interface SessionFactory {

    /**
     * Opens a {@code session} channel. The returned {@link Session} instance allows {@link Session#exec(String)
     * executing a remote command}, {@link Session#startSubsystem(String) starting a subsystem}, or {@link
     * Session#startShell() starting a shell}.
     *
     * @return the opened {@code session} channel
     *
     * @throws SSHException
     * @see Session
     */
    Session startSession()
            throws SSHException;

}
