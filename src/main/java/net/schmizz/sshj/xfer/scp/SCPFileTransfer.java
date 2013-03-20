/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.xfer.scp;

import net.schmizz.sshj.connection.channel.direct.SessionFactory;
import net.schmizz.sshj.xfer.AbstractFileTransfer;
import net.schmizz.sshj.xfer.FileSystemFile;
import net.schmizz.sshj.xfer.FileTransfer;
import net.schmizz.sshj.xfer.LocalDestFile;
import net.schmizz.sshj.xfer.LocalSourceFile;

import java.io.IOException;

public class SCPFileTransfer
        extends AbstractFileTransfer
        implements FileTransfer {

    private final SessionFactory sessionFactory;

    public SCPFileTransfer(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SCPDownloadClient newSCPDownloadClient() {
        return new SCPDownloadClient(newSCPEngine());
    }

    public SCPUploadClient newSCPUploadClient() {
        return new SCPUploadClient(newSCPEngine());
    }

    private SCPEngine newSCPEngine() {
        return new SCPEngine(sessionFactory, getTransferListener());
    }

    @Override
    public void upload(String localPath, String remotePath)
            throws IOException {
        newSCPUploadClient().copy(new FileSystemFile(localPath), remotePath);
    }

    @Override
    public void download(String remotePath, String localPath)
            throws IOException {
        download(remotePath, new FileSystemFile(localPath));
    }

    @Override
    public void download(String remotePath, LocalDestFile localFile)
            throws IOException {
        newSCPDownloadClient().copy(remotePath, localFile);
    }

    @Override
    public void upload(LocalSourceFile localFile, String remotePath)
            throws IOException {
        newSCPUploadClient().copy(localFile, remotePath);
    }

}
