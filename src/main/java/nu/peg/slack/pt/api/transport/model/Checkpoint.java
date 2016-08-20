package nu.peg.slack.pt.api.transport.model;

import lombok.Data;

@Data
public class Checkpoint {

    private Location station;
    private String arrival, departure, platform;
    private Prognosis prognosis;

}
