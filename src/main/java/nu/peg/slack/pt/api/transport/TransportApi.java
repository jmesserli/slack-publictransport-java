package nu.peg.slack.pt.api.transport;

import com.google.gson.Gson;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import nu.peg.slack.pt.api.transport.model.Connection;
import nu.peg.slack.pt.api.transport.model.ConnectionsResponse;
import nu.peg.slack.pt.api.transport.model.Location;
import nu.peg.slack.pt.api.transport.model.LocationsResponse;

import org.jvnet.hk2.annotations.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * Convenience method for calling {@link #queryConnections(String, String, LocalDateTime,
     * boolean)}. Assumes the current time as departure time
     *
     * @param from Where the connection begins
     * @param to   Where the connection ends
     */
    public List<Connection> queryConnections(String from, String to) {
        return queryConnections(from, to, LocalDateTime.now(), false);
    }

    /**
     * Convenience method for calling {@link #queryConnections(Map)}
     *
     * @param from          Where the connection begins
     * @param to            Where the connection ends
     * @param dateTime      Desired arrival or departure time
     * @param isArrivalTime true means that dateTime is the arrival time, otherwise it is the
     *                      departure time
     */
    public List<Connection> queryConnections(String from, String to, LocalDateTime dateTime, boolean isArrivalTime) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("from", from);
        queryParams.put("to", to);
        queryParams.put("date", dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        queryParams.put("time", dateTime.format(DateTimeFormatter.ofPattern("HH:mm")));
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
