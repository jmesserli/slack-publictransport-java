package nu.peg.slack.pt.util

import nu.peg.slack.pt.App
import nu.peg.slack.pt.api.transport.TransportApi

import java.math.BigInteger
import java.security.MessageDigest
import java.time.LocalDateTime

object Util {
    fun transportToSimpleTime(transportApiTime: String): String {
        return LocalDateTime.parse(transportApiTime, TransportApi.TRANSPORT_API_DATE_TIME_FORMATTER).format(App.SIMPLE_TIME_FORMATTER)
    }

    fun makeCallbackId(makeCallbackFor: Any): String {
        val hashCode = makeCallbackFor.hashCode()
        return sha1(String.format("%d%d", hashCode, System.currentTimeMillis()))
    }

    fun sha1(toHash: String): String {
        try {
            val sha1 = MessageDigest.getInstance("SHA-1")
            val bytes = sha1.digest(toHash.toByteArray(charset("UTF-8")))

            return BigInteger(1, bytes).toString(16)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}
