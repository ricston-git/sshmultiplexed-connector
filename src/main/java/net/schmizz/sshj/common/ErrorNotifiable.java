/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.common;

import java.util.Collection;

/** API for classes that are capable of being notified on an error so they can cleanup. */
public interface ErrorNotifiable {

    /** Utility functions. */
    class Util {

        /** Notify all {@code notifiables} of given {@code error}. */
        public static void alertAll(SSHException error, ErrorNotifiable... notifiables) {
            for (ErrorNotifiable notifiable : notifiables)
                notifiable.notifyError(error);
        }

        /** Notify all {@code notifiables} of given {@code error}. */
        public static void alertAll(SSHException error, Collection<? extends ErrorNotifiable> notifiables) {
            for (ErrorNotifiable notifiable : notifiables)
                notifiable.notifyError(error);
        }
    }

    /** Notifies this object of an {@code error}. */
    void notifyError(SSHException error);

}
