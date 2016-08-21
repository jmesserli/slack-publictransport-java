package nu.peg.slack.pt.model;

import nu.peg.slack.pt.api.transport.model.Location;

import java.util.List;

import lombok.Data;

@Data
public class Locations {

    private String originalFrom, originalTo;
    private Location from, to;
    private List<Location> fromOptions, toOptions;

}
