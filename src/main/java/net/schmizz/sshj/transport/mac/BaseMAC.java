/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.transport.mac;

import net.schmizz.sshj.common.SSHRuntimeException;
import net.schmizz.sshj.common.SecurityUtils;

import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;

/** Base class for <code>MAC</code> implementations based on the JCE provider. */
public class BaseMAC
        implements MAC {

    private final String algorithm;
    private final int defbsize;
    private final int bsize;
    private final byte[] tmp;
    private javax.crypto.Mac mac;

    public BaseMAC(String algorithm, int bsize, int defbsize) {
        this.algorithm = algorithm;
        this.bsize = bsize;
        this.defbsize = defbsize;
        tmp = new byte[defbsize];
    }

    @Override
    public byte[] doFinal() {
        return mac.doFinal();
    }

    @Override
    public byte[] doFinal(byte[] input) {
        return mac.doFinal(input);
    }

    @Override
    public void doFinal(byte[] buf, int offset) {
        try {
            if (bsize != defbsize) {
                mac.doFinal(tmp, 0);
                System.arraycopy(tmp, 0, buf, offset, bsize);
            } else
                mac.doFinal(buf, offset);
        } catch (ShortBufferException e) {
            throw new SSHRuntimeException(e);
        }
    }

    @Override
    public int getBlockSize() {
        return bsize;
    }

    @Override
    public void init(byte[] key) {
        if (key.length > defbsize) {
            byte[] tmp = new byte[defbsize];
            System.arraycopy(key, 0, tmp, 0, defbsize);
            key = tmp;
        }

        SecretKeySpec skey = new SecretKeySpec(key, algorithm);
        try {
            mac = SecurityUtils.getMAC(algorithm);
            mac.init(skey);
        } catch (GeneralSecurityException e) {
            throw new SSHRuntimeException(e);
        }
    }

    @Override
    public void update(byte foo[], int s, int l) {
        mac.update(foo, s, l);
    }

    @Override
    public void update(byte[] foo) {
        mac.update(foo, 0, foo.length);
    }

    @Override
    public void update(long i) {
        tmp[0] = (byte) (i >>> 24);
        tmp[1] = (byte) (i >>> 16);
        tmp[2] = (byte) (i >>> 8);
        tmp[3] = (byte) i;
        update(tmp, 0, 4);
    }

}
