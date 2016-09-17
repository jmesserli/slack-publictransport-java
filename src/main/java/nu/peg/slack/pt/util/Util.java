package nu.peg.slack.pt.util;

import nu.peg.slack.pt.App;
import nu.peg.slack.pt.api.transport.TransportApi;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.time.LocalDateTime;

public final class Util {
    public static String transportToSimpleTime(String transportApiTime) {
        return LocalDateTime.parse(transportApiTime, TransportApi.TRANSPORT_API_DATE_TIME_FORMATTER).format(App.SIMPLE_TIME_FORMATTER);
    }

    public static String makeCallbackId(Object makeCallbackFor) {
        int hashCode = makeCallbackFor.hashCode();
        return sha1(String.format("%d%d", hashCode, System.currentTimeMillis()));
    }

    public static String sha1(String toHash) {
        try {
            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
            byte[] bytes = sha1.digest(toHash.getBytes("UTF-8"));

            return new BigInteger(1, bytes).toString(16);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
