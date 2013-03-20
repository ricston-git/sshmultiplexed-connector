/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.sftp;

import net.schmizz.sshj.sftp.Response.StatusCode;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RemoteDirectory
        extends RemoteResource {

    public RemoteDirectory(Requester requester, String path, String handle) {
        super(requester, path, handle);
    }

    public List<RemoteResourceInfo> scan(RemoteResourceFilter filter)
            throws IOException {
        List<RemoteResourceInfo> rri = new LinkedList<RemoteResourceInfo>();
        loop:
        for (; ; ) {
            final Response res = requester.request(newRequest(PacketType.READDIR))
                    .retrieve(requester.getTimeoutMs(), TimeUnit.MILLISECONDS);
            switch (res.getType()) {

                case NAME:
                    final int count = res.readUInt32AsInt();
                    for (int i = 0; i < count; i++) {
                        final String name = res.readString();
                        res.readString(); // long name - IGNORED - shdve never been in the protocol
                        final FileAttributes attrs = res.readFileAttributes();
                        final PathComponents comps = requester.getPathHelper().getComponents(path, name);
                        final RemoteResourceInfo inf = new RemoteResourceInfo(comps, attrs);
                        if (!(name.equals(".") || name.equals("..")) && (filter == null || filter.accept(inf)))
                            rri.add(inf);
                    }
                    break;

                case STATUS:
                    res.ensureStatusIs(StatusCode.EOF);
                    break loop;

                default:
                    throw new SFTPException("Unexpected packet: " + res.getType());
            }
        }
        return rri;
    }

}
