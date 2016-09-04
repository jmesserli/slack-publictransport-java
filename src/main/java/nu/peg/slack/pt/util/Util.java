package nu.peg.slack.pt.util;

import nu.peg.slack.pt.App;
import nu.peg.slack.pt.api.transport.TransportApi;

import java.time.LocalDateTime;

public final class Util {
    public static String transportToSimpleTime(String transportApiTime) {
        return LocalDateTime.parse(transportApiTime, TransportApi.TRANSPORT_API_DATE_TIME_FORMATTER).format(App.SIMPLE_TIME_FORMATTER);
    }
}
