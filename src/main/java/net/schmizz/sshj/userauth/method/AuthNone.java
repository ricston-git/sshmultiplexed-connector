/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.userauth.method;

/**
 * {@code none} auth. No authentication information is exchanged in the request packet save username and the next
 * service requested. This method generally fails and is typically used to find out the method allowed by an SSH server
 * (sent as part of the {@code SSH_MSG_USERAUTH_FAILURE} packet)
 */
public class AuthNone
        extends AbstractAuthMethod {

    public AuthNone() {
        super("none");
    }

}
