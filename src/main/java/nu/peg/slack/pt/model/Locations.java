package nu.peg.slack.pt.model;

import nu.peg.slack.pt.api.transport.model.Location;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Locations {

    private String originalFrom, originalTo;
    private Location from, to;
    private List<Location> fromOptions, toOptions;

    public Locations(String originalFrom, String originalTo, List<Location> fromOptions, List<Location> toOptions) {
        this.originalFrom = originalFrom;
        this.originalTo = originalTo;
        this.fromOptions = fromOptions;
        this.toOptions = toOptions;

        if (fromOptions.size() == 1) {
            from = fromOptions.get(0);
        }

        if (toOptions.size() == 1) {
            to = toOptions.get(0);
        }
    }

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
