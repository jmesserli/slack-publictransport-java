package nu.peg.slack.pt.api.transport.model;

import java.util.List;

import lombok.Data;

@Data
public class Journey {

    private String name, category, number, operator, to;
    private Integer categoryCode;
    private List<Checkpoint> passList;
    private Integer capacity1st, capacity2nd;

}
