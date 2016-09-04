package nu.peg.slack.pt.api.transport;

import com.google.gson.Gson;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import nu.peg.slack.pt.api.transport.model.*;
import nu.peg.slack.pt.model.ConnectionRequest;

import org.jvnet.hk2.annotations.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class TransportApi {

    private Gson gson;

    public TransportApi() {
        gson = new Gson();
    }

    /**
     * Queries the api endpoint at <a href="http://transport.opendata.ch/docs.html#locations">/v1/locations</a>
     * for locations
     *
     * @param query Name of the location to search for
     */
    public List<Location> queryLocation(String query) {
        HttpResponse<String> stringResponse = null;

        try {
            stringResponse = Unirest.get("http://transport.opendata.ch/v1/locations")
                                    .queryString("query", query)
                                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        if (stringResponse == null || stringResponse.getStatus() != 200) {
            return null;
        }

        LocationsResponse response = gson.fromJson(stringResponse.getBody(), LocationsResponse.class);
        return response.getStations();
    }

    /**
     * Convenience method for calling {@link #queryConnections(String, String, LocalTime, boolean)}
     * using a {@link ConnectionRequest}
     *
     * @param request The {@link ConnectionRequest} to take the data from
     */
    public List<Connection> queryConnections(ConnectionRequest request) {
        return queryConnections(request.getLocations().getFrom().getName(), request.getLocations().getTo().getName(), request.getTime(), request.isArrivalTime());
    }

    /**
     * Convenience method for calling {@link #queryConnections(String, String, LocalTime,
     * boolean)}. Assumes the current time as departure time
     *
     * @param from Where the connection begins
     * @param to   Where the connection ends
     */
    public List<Connection> queryConnections(String from, String to) {
        return queryConnections(from, to, LocalTime.now(), false);
    }

    /**
     * Convenience method for calling {@link #queryConnections(Map)}
     *
     * @param from          Where the connection begins
     * @param to            Where the connection ends
     * @param localTime     Desired arrival or departure time
     * @param isArrivalTime true means that dateTime is the arrival time, otherwise it is the
     *                      departure time
     */
    public List<Connection> queryConnections(String from, String to, LocalTime localTime, boolean isArrivalTime) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("from", from);
        queryParams.put("to", to);
        queryParams.put("date", localTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        queryParams.put("time", localTime.format(DateTimeFormatter.ofPattern("HH:mm")));
        queryParams.put("isArrivalTime", isArrivalTime);

        return queryConnections(queryParams);
    }

    /**
     * Queries the transport api endpoint at <a href="http://transport.opendata.ch/docs.html#connections">/v1/connections</a>
     * for connections
     *
     * @param queryParams Parameters for the api. See <a href="http://transport.opendata.ch/docs.html#connections">the
     *                    docs</a> for more information
     */
    public List<Connection> queryConnections(Map<String, Object> queryParams) {
        HttpResponse<String> stringResponse = null;

        try {
            stringResponse = Unirest.get("http://transport.opendata.ch/v1/connections")
                                    .queryString(queryParams)
                                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        if (stringResponse == null || stringResponse.getStatus() != 200) {
            return null;
        }

        ConnectionsResponse response = gson.fromJson(stringResponse.getBody(), ConnectionsResponse.class);
        return response.getConnections();
    }

}
