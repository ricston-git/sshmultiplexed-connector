/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package net.schmizz.sshj.connection.channel;

import net.schmizz.sshj.connection.ConnectionException;

public class OpenFailException
        extends ConnectionException {

    public enum Reason {
        UNKNOWN(0),
        ADMINISTRATIVELY_PROHIBITED(1),
        CONNECT_FAILED(2),
        UNKNOWN_CHANNEL_TYPE(3),
        RESOURCE_SHORTAGE(4);

        public static Reason fromInt(int code) {
            for (Reason rc : Reason.values())
                if (rc.code == code)
                    return rc;
            return UNKNOWN;
        }

        private final int code;

        private Reason(int rc) {
            this.code = rc;
        }

        public int getCode() {
            return code;
        }

    }

    private final String channelType;
    private final Reason reason;
    private final String message;

    public OpenFailException(String channelType, int reasonCode, String message) {
        super(message);
        this.channelType = channelType;
        this.reason = Reason.fromInt(reasonCode);
        this.message = message;
    }

    public OpenFailException(String channelType, Reason reason, String message) {
        super(message);
        this.channelType = channelType;
        this.reason = reason;
        this.message = message;
    }

    public String getChannelType() {
        return channelType;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Reason getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "Opening `" + channelType + "` channel failed: " + getMessage();
    }

}