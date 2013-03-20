/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.userauth.password;

import java.util.Arrays;

/** Static utility method and factories */
public class PasswordUtils {

    /**
     * Blank out a character array
     *
     * @param pwd the character array
     */
    public static void blankOut(char[] pwd) {
        if (pwd != null)
            Arrays.fill(pwd, ' ');
    }

    /**
     * @param password the password as a char[]
     *
     * @return the constructed {@link PasswordFinder}
     */
    public static PasswordFinder createOneOff(final char[] password) {
        if (password == null)
            return null;
        else
            return new PasswordFinder() {
                @Override
                public char[] reqPassword(Resource<?> resource) {
                    char[] cloned = password.clone();
                    blankOut(password);
                    return cloned;
                }

                @Override
                public boolean shouldRetry(Resource<?> resource) {
                    return false;
                }
            };
    }

}
