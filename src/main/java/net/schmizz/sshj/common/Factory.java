/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.common;

import java.util.LinkedList;
import java.util.List;

/**
 * A basic factory interface.
 *
 * @param <T> the type of object created by this factory
 */
public interface Factory<T> {

    /**
     * Inteface for a named factory. Named factories are simply factories that are identified by a name. Such names are
     * used mainly in SSH algorithm negotiation.
     *
     * @param <T> type of object created by this factory
     */
    interface Named<T>
            extends Factory<T> {

        /** Utility functions */
        public static class Util {

            /**
             * Creates an object by picking a factory from {@code factories} that is identified by {@code name} from a
             * list of named {@code factories}. Uses the first match.
             *
             * @param factories list of available factories
             * @param name      name of the desired factory
             * @param <T>       type of the {@code factories}
             *
             * @return a newly created instance of {@code T} or {@code null} if there was no match
             */
            public static <T> T create(List<Named<T>> factories, String name) {
                if (factories != null)
                    for (Named<T> f : factories)
                        if (f.getName().equals(name))
                            return f.create();
                return null;
            }

            /**
             * Retrieve a particular factory as identified by {@code name} from a list of named {@code factories}.
             * Returns the first match.
             *
             * @param factories list of factories
             * @param name      the name of the factory to retrieve
             * @param <T>       type of the {@code factories}
             *
             * @return a factory or {@code null} if there was no match
             */
            public static <T> Named<T> get(List<Named<T>> factories, String name) {
                for (Named<T> f : factories)
                    if (f.getName().equals(name))
                        return f;
                return null;
            }

            /**
             * Get a comma-delimited string containing the factory names from the given list of {@code factories}.
             *
             * @param factories list of available factories
             * @param <T>       type of the {@code factories}
             *
             * @return a comma separated list of factory names
             */
            public static <T> List<String> getNames(List<Named<T>> factories) {
                List<String> list = new LinkedList<String>();
                for (Named<T> f : factories)
                    list.add(f.getName());
                return list;
            }

        }

        /** @return the name of this factory. */
        String getName();

    }

    /** @return a new object created using this factory. */
    T create();

}
