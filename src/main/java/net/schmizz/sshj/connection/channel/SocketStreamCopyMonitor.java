/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.connection.channel;

import net.schmizz.concurrent.Event;
import net.schmizz.sshj.common.IOUtils;

import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class SocketStreamCopyMonitor
        extends Thread {

    private SocketStreamCopyMonitor(Runnable r) {
        super(r);
        setName("sockmon");
        setDaemon(true);
    }

    private static Closeable wrapSocket(final Socket socket) {
        return new Closeable() {
            @Override
            public void close()
                    throws IOException {
                socket.close();
            }
        };
    }

    public static void monitor(final int frequency, final TimeUnit unit,
                               final Event<IOException> x, final Event<IOException> y,
                               final Channel channel, final Socket socket) {
        new SocketStreamCopyMonitor(new Runnable() {
            public void run() {
                try {
                    for (Event<IOException> ev = x;
                         !ev.tryAwait(frequency, unit);
                         ev = (ev == x) ? y : x) {
                    }
                } catch (IOException ignored) {
                } finally {
                    IOUtils.closeQuietly(channel, wrapSocket(socket));
                }
            }
        }).start();
    }

}
