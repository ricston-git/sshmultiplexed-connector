/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.xfer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public abstract class InMemoryDestFile
        implements LocalDestFile {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public InMemoryDestFile getTargetFile(String filename)
            throws IOException {
        return this;
    }

    @Override
    public void setLastAccessedTime(long t)
            throws IOException {
        log.info("atime = {}", t);
    }

    @Override
    public void setLastModifiedTime(long t)
            throws IOException {
        log.info("mtime = {}", t);
    }

    @Override
    public void setPermissions(int perms)
            throws IOException {
        log.info("permissions = {}", Integer.toOctalString(perms));
    }

    @Override
    public LocalDestFile getTargetDirectory(String dirname)
            throws IOException {
        throw new AssertionError("Unimplemented");
    }

    @Override
    public LocalDestFile getChild(String name) {
        throw new AssertionError("Unimplemented");
    }

}
