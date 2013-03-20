/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.connection.channel.direct;

/** Various signals that may be sent or received. The signals are from POSIX and simply miss the {@code "SIG_"} prefix. */
public enum Signal {

    ABRT,
    ALRM,
    FPE,
    HUP,
    ILL,
    INT,
    KILL,
    PIPE,
    QUIT,
    SEGV,
    TERM,
    USR1,
    USR2,
    UNKNOWN;

    /**
     * Create from the string representation used when the signal is received as part of an SSH packet.
     *
     * @param name name of the signal as received
     *
     * @return the enum constant inferred
     */
    public static Signal fromString(String name) {
        for (Signal sig : Signal.values())
            if (sig.toString().equals(name))
                return sig;
        return UNKNOWN;
    }

}