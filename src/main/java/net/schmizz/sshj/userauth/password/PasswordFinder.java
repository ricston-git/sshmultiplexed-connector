/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.userauth.password;

/** Services requests for plaintext passwords. */
public interface PasswordFinder {

    /**
     * Request password for specified resource.
     * <p/>
     * This method may return {@code null} when the request cannot be serviced, e.g. when the user cancels a password
     * prompt.
     *
     * @param resource the resource for which password is being requested
     *
     * @return the password or {@code null}
     */
    char[] reqPassword(Resource<?> resource);

    /**
     * If password turns out to be incorrect, indicates whether another call to {@link #reqPassword(Resource)} should be
     * made.
     * <p/>
     * This method is geared at interactive implementations, and stub implementations may simply return {@code false}.
     *
     * @param resource the resource for which password is being requested
     *
     * @return whether to retry requesting password for a particular resource
     */
    boolean shouldRetry(Resource<?> resource);

}
