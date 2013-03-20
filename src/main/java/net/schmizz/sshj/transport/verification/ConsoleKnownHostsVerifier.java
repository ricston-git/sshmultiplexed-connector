/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.transport.verification;

import net.schmizz.sshj.common.KeyType;
import net.schmizz.sshj.common.SecurityUtils;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.security.PublicKey;

public class ConsoleKnownHostsVerifier
        extends OpenSSHKnownHosts {

    private static final String YES = "yes";
    private static final String NO = "no";

    private final Console console;

    public ConsoleKnownHostsVerifier(File khFile, Console console)
            throws IOException {
        super(khFile);
        this.console = console;
    }

    @Override
    protected boolean hostKeyUnverifiableAction(String hostname, PublicKey key) {
        final KeyType type = KeyType.fromKey(key);
        console.printf("The authenticity of host '%s' can't be established.\n" +
                               "%s key fingerprint is %s.\n", hostname, type, SecurityUtils.getFingerprint(key));
        String response = console.readLine("Are you sure you want to continue connecting (yes/no)? ");
        while (!(response.equalsIgnoreCase(YES) || response.equalsIgnoreCase(NO))) {
            response = console.readLine("Please explicitly enter yes/no: ");
        }
        if (response.equalsIgnoreCase(YES)) {
            try {
                entries().add(new SimpleEntry(null, hostname, KeyType.fromKey(key), key));
                write();
                console.printf("Warning: Permanently added '%s' (%s) to the list of known hosts.\n", hostname, type);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }

    @Override
    protected boolean hostKeyChangedAction(HostEntry entry, String hostname, PublicKey key) {
        final KeyType type = KeyType.fromKey(key);
        final String fp = SecurityUtils.getFingerprint(key);
        final String path = getFile().getAbsolutePath();
        console.printf(
                "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                        "@    WARNING: REMOTE HOST IDENTIFICATION HAS CHANGED!     @\n" +
                        "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                        "IT IS POSSIBLE THAT SOMEONE IS DOING SOMETHING NASTY!\n" +
                        "Someone could be eavesdropping on you right now (man-in-the-middle attack)!\n" +
                        "It is also possible that the host key has just been changed.\n" +
                        "The fingerprint for the %s key sent by the remote host is\n" +
                        "%s.\n" +
                        "Please contact your system administrator or" +
                        "add correct host key in %s to get rid of this message.\n",
                type, fp, path);
        return false;
    }

}
