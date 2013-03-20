/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.xfer.scp;

import net.schmizz.sshj.common.SSHException;

public class SCPException
        extends SSHException {

    public SCPException(String message) {
        super(message);
    }

    public SCPException(String message, Throwable cause) {
        super(message, cause);
    }
}