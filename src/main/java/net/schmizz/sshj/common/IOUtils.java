/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class IOUtils {

    private static final Logger LOG = LoggerFactory.getLogger(IOUtils.class);

    public static final Charset UTF8 = Charset.forName("UTF-8");

    public static void closeQuietly(Closeable... closeables) {
        for (Closeable c : closeables)
            try {
                if (c != null)
                    c.close();
            } catch (IOException logged) {
                LOG.warn("Error closing {} - {}", c, logged);
            }
    }

    public static ByteArrayOutputStream readFully(InputStream stream)
            throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        new StreamCopier(stream, baos).copy();
        return baos;
    }

}