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

public abstract class InMemorySourceFile
        implements LocalSourceFile {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public boolean isFile() {
        return true;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public int getPermissions()
            throws IOException {
        return 0644;
    }

    @Override
    public boolean providesAtimeMtime() {
        return false;
    }

    @Override
    public long getLastAccessTime()
            throws IOException {
        throw new AssertionError("Unimplemented");
    }

    @Override
    public long getLastModifiedTime()
            throws IOException {
        throw new AssertionError("Unimplemented");
    }

    @Override
    public Iterable<? extends LocalSourceFile> getChildren(LocalFileFilter filter)
            throws IOException {
        throw new AssertionError("Unimplemented");
    }

}
