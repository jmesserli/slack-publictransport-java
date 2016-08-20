package nu.peg.slack.pt.api.transport.model;

import java.util.List;

public class LocationsResponse {
    private List<Location> stations;

    public LocationsResponse() {
    }

    public LocationsResponse(List<Location> stations) {
        this.stations = stations;
    }

    public List<Location> getStations() {
        return stations;
    }

    public void setStations(List<Location> stations) {
        this.stations = stations;
    }
}
