/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.transport.cipher;

/** Wrapper for a cryptographic cipher, used either for encryption or decryption. */
public interface Cipher {

    enum Mode {
        Encrypt,
        Decrypt
    }

    /** @return the block size for this cipher */
    int getBlockSize();

    /** @return the size of the initialization vector */
    int getIVSize();

    /**
     * Initialize the cipher for encryption or decryption with the given private key and initialization vector
     *
     * @param mode whether this instance wil encrypt or decrypt
     * @param key  the key for the cipher
     * @param iv   initialization vector
     */
    void init(Mode mode, byte[] key, byte[] iv);

    /**
     * Performs in-place encryption or decryption on the given data.
     *
     * @param input       the subject
     * @param inputOffset offset at which to start
     * @param inputLen    number of bytes starting at {@code inputOffset}
     */
    void update(byte[] input, int inputOffset, int inputLen);

}
