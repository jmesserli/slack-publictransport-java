package nu.peg.slack.pt.api.transport;

import com.google.gson.Gson;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import nu.peg.slack.pt.api.transport.model.Location;
import nu.peg.slack.pt.api.transport.model.LocationsResponse;

import java.util.List;

public class TransportApi {

    private Gson gson;

    public TransportApi() {
        gson = new Gson();
    }

    public List<Location> queryLocation(String query) {
        HttpResponse<String> jsonResponse = null;

        try {
            jsonResponse = Unirest.get("http://transport.opendata.ch/v1/locations")
                                  .queryString("query", query)
                                  .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        if (jsonResponse == null || jsonResponse.getStatus() != 200) {
            return null;
        }

        LocationsResponse response = gson.fromJson(jsonResponse.getBody(), LocationsResponse.class);
        return response.getStations();
    }

}
