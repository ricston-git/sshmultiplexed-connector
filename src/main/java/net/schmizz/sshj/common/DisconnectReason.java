/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.common;

/** Disconnect error codes */
public enum DisconnectReason {

    UNKNOWN,
    HOST_NOT_ALLOWED_TO_CONNECT,
    PROTOCOL_ERROR,
    KEY_EXCHANGE_FAILED,
    RESERVED,
    MAC_ERROR,
    COMPRESSION_ERROR,
    SERVICE_NOT_AVAILABLE,
    PROTOCOL_VERSION_NOT_SUPPORTED,
    HOST_KEY_NOT_VERIFIABLE,
    CONNECTION_LOST,
    BY_APPLICATION,
    TOO_MANY_CONNECTIONS,
    AUTH_CANCELLED_BY_USER,
    NO_MORE_AUTH_METHODS_AVAILABLE,
    ILLEGAL_USER_NAME;

    public static DisconnectReason fromInt(int code) {
        final int len = values().length;
        if (code < 0 || code > len)
            return UNKNOWN;
        return values()[code];
    }

    public int toInt() {
        return ordinal();
    }

}
