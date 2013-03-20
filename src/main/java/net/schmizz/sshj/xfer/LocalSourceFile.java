/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.xfer;

import java.io.IOException;
import java.io.InputStream;

public interface LocalSourceFile {

    String getName();

    long getLength();

    InputStream getInputStream()
            throws IOException;

    /**
     * Returns the permissions for the underlying file
     *
     * @return permissions e.g. 0644
     *
     * @throws IOException
     */
    int getPermissions()
            throws IOException;

    boolean isFile();

    boolean isDirectory();

    Iterable<? extends LocalSourceFile> getChildren(LocalFileFilter filter)
            throws IOException;

    boolean providesAtimeMtime();

    /**
     * Returns last access time for the underlying file.
     *
     * @return time in seconds since Unix epoch
     *
     * @throws IOException
     */
    long getLastAccessTime()
            throws IOException;

    /**
     * Returns last access time for the underlying file.
     *
     * @return time in seconds since Unix epoch
     *
     * @throws IOException
     */
    long getLastModifiedTime()
            throws IOException;

}
