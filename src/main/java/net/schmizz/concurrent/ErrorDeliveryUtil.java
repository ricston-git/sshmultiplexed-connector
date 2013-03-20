/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.concurrent;

import java.util.Collection;

public class ErrorDeliveryUtil {

    public static void alertPromises(Throwable x, Promise... promises) {
        for (Promise p : promises)
            p.deliverError(x);
    }

    public static void alertPromises(Throwable x, Collection<? extends Promise> promises) {
        for (Promise p : promises)
            p.deliverError(x);
    }

    public static void alertEvents(Throwable x, Event... events) {
        for (Event e : events)
            e.deliverError(x);
    }

    public static void alertEvents(Throwable x, Collection<? extends Event> events) {
        for (Event e : events)
            e.deliverError(x);
    }

}
