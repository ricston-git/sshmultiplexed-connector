/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.sftp;

import java.util.Set;

public enum OpenMode {

    /** Open the file for reading. */
    READ(0x00000001),
    /**
     * Open the file for writing. If both this and {@link OpenMode#READ} are specified, the file is opened for both
     * reading and writing.
     */
    WRITE(0x00000002),
    /** Force all writes to append data at the end of the file. */
    APPEND(0x00000004),
    /**
     * If this flag is specified, then a new file will be created if one does not already exist (if {@link
     * OpenMode#TRUNC} is specified, the new file will be truncated to zero length if it previously exists).
     */
    CREAT(0x00000008),
    /**
     * Forces an existing file with the same name to be truncated to zero length when creating a file by specifying
     * {@link OpenMode#CREAT}. {@link OpenMode#CREAT} MUST also be specified if this flag is used.
     */
    TRUNC(0x00000010),
    /**
     * Causes the request to fail if the named file already exists. {@link OpenMode#CREAT} MUST also be specified if
     * this flag is used.
     */
    EXCL(0x00000020);

    private final int pflag;

    private OpenMode(int pflag) {
        this.pflag = pflag;
    }

    public static int toMask(Set<OpenMode> modes) {
        int mask = 0;
        for (OpenMode m : modes)
            mask |= m.pflag;
        return mask;
    }

}
