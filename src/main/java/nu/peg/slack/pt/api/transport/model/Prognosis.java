package nu.peg.slack.pt.api.transport.model;

import lombok.Data;

@Data
public class Prognosis {

    private String platform, departure, arrival;
    private Integer capacity1st, capacity2nd;

}
