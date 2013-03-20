/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.transport.cipher;

import net.schmizz.sshj.common.SSHRuntimeException;
import net.schmizz.sshj.common.SecurityUtils;

import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;

/** Base class for all Cipher implementations delegating to the JCE provider. */
public class BaseCipher
        implements Cipher {

    private static byte[] resize(byte[] data, int size) {
        if (data.length > size) {
            final byte[] tmp = new byte[size];
            System.arraycopy(data, 0, tmp, 0, size);
            data = tmp;
        }
        return data;
    }

    private final int ivsize;
    private final int bsize;
    private final String algorithm;
    private final String transformation;

    private javax.crypto.Cipher cipher;

    public BaseCipher(int ivsize, int bsize, String algorithm, String transformation) {
        this.ivsize = ivsize;
        this.bsize = bsize;
        this.algorithm = algorithm;
        this.transformation = transformation;
    }

    @Override
    public int getBlockSize() {
        return bsize;
    }

    @Override
    public int getIVSize() {
        return ivsize;
    }

    @Override
    public void init(Mode mode, byte[] key, byte[] iv) {
        key = BaseCipher.resize(key, bsize);
        iv = BaseCipher.resize(iv, ivsize);
        try {
            cipher = SecurityUtils.getCipher(transformation);
            cipher.init((mode == Mode.Encrypt ? javax.crypto.Cipher.ENCRYPT_MODE : javax.crypto.Cipher.DECRYPT_MODE),
                        new SecretKeySpec(key, algorithm), new IvParameterSpec(iv));
        } catch (GeneralSecurityException e) {
            cipher = null;
            throw new SSHRuntimeException(e);
        }
    }

    @Override
    public void update(byte[] input, int inputOffset, int inputLen) {
        try {
            cipher.update(input, inputOffset, inputLen, input, inputOffset);
        } catch (ShortBufferException e) {
            throw new SSHRuntimeException(e);
        }
    }

}
