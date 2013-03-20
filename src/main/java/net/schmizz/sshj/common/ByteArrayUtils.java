/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.common;

/** Utility functions for byte arrays. */
public class ByteArrayUtils {

    final static char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * Check whether some part or whole of two byte arrays is equal, for <code>length</code> bytes starting at some
     * offset.
     *
     * @param a1
     * @param a1Offset
     * @param a2
     * @param a2Offset
     * @param length
     *
     * @return <code>true</code> or <code>false</code>
     */
    public static boolean equals(byte[] a1, int a1Offset, byte[] a2, int a2Offset, int length) {
        if (a1.length < a1Offset + length || a2.length < a2Offset + length)
            return false;
        while (length-- > 0)
            if (a1[a1Offset++] != a2[a2Offset++])
                return false;
        return true;
    }

    /**
     * Get a hexadecimal representation of a byte array starting at <code>offset</code> index for <code>len</code>
     * bytes, with each octet separated by a space.
     *
     * @param array
     * @param offset
     * @param len
     *
     * @return hex string, each octet delimited by a space
     */
    public static String printHex(byte[] array, int offset, int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            byte b = array[offset + i];
            if (sb.length() > 0)
                sb.append(' ');
            sb.append(digits[b >> 4 & 0x0F]);
            sb.append(digits[b & 0x0F]);
        }
        return sb.toString();
    }

    /**
     * Get the hexadecimal representation of a byte array.
     *
     * @param array
     *
     * @return hex string
     */
    public static String toHex(byte[] array) {
        return toHex(array, 0, array.length);
    }

    /**
     * Get the hexadecimal representation of a byte array starting at <code>offset</code> index for <code>len</code>
     * bytes.
     *
     * @param array
     * @param offset
     * @param len
     *
     * @return hex string
     */
    public static String toHex(byte[] array, int offset, int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            byte b = array[offset + i];
            sb.append(digits[b >> 4 & 0x0F]);
            sb.append(digits[b & 0x0F]);
        }
        return sb.toString();
    }

}
