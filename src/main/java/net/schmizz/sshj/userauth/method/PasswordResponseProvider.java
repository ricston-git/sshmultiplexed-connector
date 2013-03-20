/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.userauth.method;

import net.schmizz.sshj.userauth.password.PasswordFinder;
import net.schmizz.sshj.userauth.password.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class PasswordResponseProvider
        implements ChallengeResponseProvider {

    public static final Pattern DEFAULT_PROMPT_PATTERN = Pattern.compile(".*[pP]assword:\\s?\\z");

    private final Logger log = LoggerFactory.getLogger(getClass());

    private static final char[] EMPTY_RESPONSE = new char[0];

    private final Pattern promptPattern;
    private final PasswordFinder pwdf;

    private Resource resource;

    public PasswordResponseProvider(PasswordFinder pwdf) {
        this(pwdf, DEFAULT_PROMPT_PATTERN);
    }

    public PasswordResponseProvider(PasswordFinder pwdf, Pattern promptPattern) {
        this.pwdf = pwdf;
        this.promptPattern = promptPattern;
    }

    @Override
    public List<String> getSubmethods() {
        return Collections.emptyList();
    }

    @Override
    public void init(Resource resource, String name, String instruction) {
        this.resource = resource;
        log.debug("Challenge - name=`{}`; instruction=`{}`", name, instruction);
    }

    @Override
    public char[] getResponse(String prompt, boolean echo) {
        if (!echo && promptPattern.matcher(prompt).matches()) {
            return pwdf.reqPassword(resource);
        }
        return EMPTY_RESPONSE;
    }

    @Override
    public boolean shouldRetry() {
        return pwdf.shouldRetry(resource);
    }

}
