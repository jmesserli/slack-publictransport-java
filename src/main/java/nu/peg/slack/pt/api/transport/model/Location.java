package nu.peg.slack.pt.api.transport.model;

import lombok.Data;

@Data
public class Location {

    private String id, name;
    private Integer score;
    private Coordinates coordinates;
    private Double distance;

}
