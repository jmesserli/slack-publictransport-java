package nu.peg.slack.pt.api.transport.model;

import lombok.Data;

import java.util.List;

@Data
public class LocationsResponse {

    private List<Location> stations;

}
