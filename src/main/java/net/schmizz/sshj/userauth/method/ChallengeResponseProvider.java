/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.userauth.method;

import net.schmizz.sshj.userauth.password.Resource;

import java.util.List;

public interface ChallengeResponseProvider {

    List<String> getSubmethods();

    void init(Resource resource, String name, String instruction);

    char[] getResponse(String prompt, boolean echo);

    boolean shouldRetry();

}
