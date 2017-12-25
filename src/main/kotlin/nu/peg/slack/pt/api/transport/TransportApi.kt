package nu.peg.slack.pt.api.transport

import com.google.gson.Gson

import com.mashape.unirest.http.HttpResponse
import com.mashape.unirest.http.Unirest
import com.mashape.unirest.http.exceptions.UnirestException

import nu.peg.slack.pt.api.transport.model.*
import nu.peg.slack.pt.model.ConnectionRequest

import org.jvnet.hk2.annotations.Service

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class TransportApi {

    private val gson: Gson

    init {
        gson = Gson()
    }

    /**
     * Queries the api endpoint at [/v1/locations](http://transport.opendata.ch/docs.html#locations)
     * for locations
     *
     * @param query Name of the location to search for
     */
    fun queryLocation(query: String): List<Location>? {
        var stringResponse: HttpResponse<String>? = null

        try {
            stringResponse = Unirest.get("http://transport.opendata.ch/v1/locations")
                    .queryString("query", query)
                    .asString()
        } catch (e: UnirestException) {
            e.printStackTrace()
        }

        if (stringResponse == null || stringResponse!!.getStatus() !== 200) {
            return null
        }

        val response = gson.fromJson(stringResponse!!.getBody(), LocationsResponse::class.java)
        return response.getStations()
    }

    /**
     * Convenience method for calling [.queryConnections]
     * using a [ConnectionRequest]
     *
     * @param request The [ConnectionRequest] to take the data from
     */
    fun queryConnections(request: ConnectionRequest): List<Connection>? {
        return queryConnections(request.getLocations().getFrom().getName(), request.getLocations().getTo().getName(), request.getTime(), request.isArrivalTime())
    }

    /**
     * Convenience method for calling [.queryConnections]
     *
     * @param from          Where the connection begins
     * @param to            Where the connection ends
     * @param localTime     Desired arrival or departure time
     * @param isArrivalTime true means that dateTime is the arrival time, otherwise it is the
     * departure time
     */
    @JvmOverloads
    fun queryConnections(from: String, to: String, localTime: LocalTime = LocalTime.now(), isArrivalTime: Boolean = false): List<Connection>? {
        val queryParams = HashMap<String, Any>()
        queryParams.put("from", from)
        queryParams.put("to", to)
        queryParams.put("date", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
        queryParams.put("time", localTime.format(DateTimeFormatter.ofPattern("HH:mm")))
        queryParams.put("isArrivalTime", isArrivalTime)

        return queryConnections(queryParams)
    }

    /**
     * Queries the transport api endpoint at [/v1/connections](http://transport.opendata.ch/docs.html#connections)
     * for connections
     *
     * @param queryParams Parameters for the api. See [the
 * docs](http://transport.opendata.ch/docs.html#connections) for more information
     */
    fun queryConnections(queryParams: Map<String, Any>): List<Connection>? {
        var stringResponse: HttpResponse<String>? = null

        try {
            stringResponse = Unirest.get("http://transport.opendata.ch/v1/connections")
                    .queryString(queryParams)
                    .asString()
        } catch (e: UnirestException) {
            e.printStackTrace()
        }

        if (stringResponse == null || stringResponse!!.getStatus() !== 200) {
            return null
        }

        val response = gson.fromJson(stringResponse!!.getBody(), ConnectionsResponse::class.java)
        return response.getConnections()
    }

    companion object {

        val TRANSPORT_API_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssx"
        val TRANSPORT_API_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(TRANSPORT_API_DATE_TIME_FORMAT)
    }

}
/**
 * Convenience method for calling [.queryConnections]. Assumes the current time as departure time
 *
 * @param from Where the connection begins
 * @param to   Where the connection ends
 */
