/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.sftp;

public class PathComponents {

    static String adjustForParent(String parent, String path, String pathSep) {
        return (path.startsWith(pathSep)) ? path // Absolute path, nothing to adjust
                : (parent + (parent.endsWith(pathSep) ? "" : pathSep) + path); // Relative path
    }

    static String trimTrailingSeparator(String somePath, String pathSep) {
        return somePath.endsWith(pathSep) ? somePath.substring(0, somePath.length() - pathSep.length()) : somePath;
    }

    private final String parent;
    private final String name;
    private final String path;

    public PathComponents(String parent, String name, String pathSep) {
        this.parent = parent;
        this.name = name;
        this.path = trimTrailingSeparator(adjustForParent(parent, name, pathSep), pathSep);
    }

    public String getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || ((o instanceof PathComponents) && path.equals(((PathComponents) o).path));
    }

    @Override
    public int hashCode() {
        return path.hashCode();
    }

    @Override
    public String toString() {
        return "[parent=" + parent + "; name=" + name + "; path=" + path + "]";
    }

}