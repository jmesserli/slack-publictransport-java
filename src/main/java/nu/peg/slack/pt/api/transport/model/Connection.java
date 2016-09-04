package nu.peg.slack.pt.api.transport.model;

import java.util.List;

import lombok.Data;

@Data
public class Connection {

    private Checkpoint from, to;
    private String duration;
    private Service service;
    private List<String> products;
    private Integer capacity1st, capacity2nd;
    private List<Section> sections;

}
