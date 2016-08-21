package nu.peg.slack.pt.service;

import nu.peg.slack.pt.api.slack.model.SlackMessage;
import nu.peg.slack.pt.model.Locations;

public interface LocationService {

    public Locations queryLocations(String from, String to);

    public SlackMessage makeRefinementMessage(Locations locations);
}
