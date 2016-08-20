package nu.peg.slack.pt.api.transport.model;

import lombok.Data;

@Data
public class Section {

    private Journey journey;
    private Walk walk;
    private Checkpoint departure, arrival;

}
