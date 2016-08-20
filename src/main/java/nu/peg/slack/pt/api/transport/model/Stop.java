package nu.peg.slack.pt.api.transport.model;

import lombok.Data;

@Data
public class Stop {

    private Location station;
    private String name, category, number, operator, to;

}
