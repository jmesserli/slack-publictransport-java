package nu.peg.slack.pt.api.transport.model;

import java.util.List;

import lombok.Data;

@Data
public class LocationsResponse {

    private List<Location> stations;

}
