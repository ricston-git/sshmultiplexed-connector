/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.xfer;

import java.io.IOException;

public interface FileTransfer {

    /**
     * This is meant to delegate to {@link #upload(LocalSourceFile, String)} with the {@code localPath} wrapped as e.g.
     * a {@link FileSystemFile}.
     *
     * @param localPath
     * @param remotePath
     *
     * @throws IOException
     */
    void upload(String localPath, String remotePath)
            throws IOException;

    /**
     * This is meant to delegate to {@link #download(String, LocalDestFile)} with the {@code localPath} wrapped as e.g.
     * a {@link FileSystemFile}.
     *
     * @param localPath
     * @param remotePath
     *
     * @throws IOException
     */
    void download(String remotePath, String localPath)
            throws IOException;

    /**
     * Upload {@code localFile} to {@code remotePath}.
     *
     * @param localFile
     * @param remotePath
     *
     * @throws IOException
     */
    void upload(LocalSourceFile localFile, String remotePath)
            throws IOException;

    /**
     * Download {@code remotePath} to {@code localFile}.
     *
     * @param localFile
     * @param remotePath
     *
     * @throws IOException
     */
    void download(String remotePath, LocalDestFile localFile)
            throws IOException;

    TransferListener getTransferListener();

    void setTransferListener(TransferListener listener);

}
