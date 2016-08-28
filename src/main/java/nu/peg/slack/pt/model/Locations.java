package nu.peg.slack.pt.model;

import lombok.Data;
import nu.peg.slack.pt.api.transport.model.Location;

import java.util.List;

@Data
public class Locations {

    private String originalFrom, originalTo;
    private Location from, to;
    private List<Location> fromOptions, toOptions;

    public boolean isUnique() {
        return from != null && to != null;
    }

    public void choose(String fromOrTo, int chosenLocationIdx) {
        if (fromOrTo.equals("from")) {
            from = fromOptions.get(chosenLocationIdx);
        } else if (fromOrTo.equals("to")) {
            to = toOptions.get(chosenLocationIdx);
        }
    }
}
