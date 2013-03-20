/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.sftp;

public class RemoteResourceInfo {

    private final PathComponents comps;
    private final FileAttributes attrs;

    public RemoteResourceInfo(PathComponents comps, FileAttributes attrs) {
        this.comps = comps;
        this.attrs = attrs;
    }

    public String getPath() {
        return comps.getPath();
    }

    public String getParent() {
        return comps.getParent();
    }

    public String getName() {
        return comps.getName();
    }

    public FileAttributes getAttributes() {
        return attrs;
    }

    public boolean isRegularFile() {
        return attrs.getType() == FileMode.Type.REGULAR;
    }

    public boolean isDirectory() {
        return attrs.getType() == FileMode.Type.DIRECTORY;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof RemoteResourceInfo && (comps.equals(((RemoteResourceInfo) o).comps));
    }

    @Override
    public int hashCode() {
        return comps.hashCode();
    }

    @Override
    public String toString() {
        return "[" + attrs.getType() + "] " + getPath();
    }

}
