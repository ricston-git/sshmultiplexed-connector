/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.userauth.password;

import java.io.IOException;
import java.io.Reader;

/** A password-protected resource */
public abstract class Resource<H> {

    private final H detail;

    public Resource(H handle) {
        this.detail = handle;
    }

    public H getDetail() {
        return detail;
    }

    public abstract Reader getReader()
            throws IOException;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Resource))
            return false;
        Resource resource = (Resource) o;
        return detail == null ? resource.detail == null : detail.equals(resource.detail);

    }

    @Override
    public int hashCode() {
        return detail != null ? detail.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "[" + getClass().getSimpleName() + "] " + detail;
    }

}
