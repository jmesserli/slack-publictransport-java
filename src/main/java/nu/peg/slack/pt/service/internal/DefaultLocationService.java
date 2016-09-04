package nu.peg.slack.pt.service.internal;

import nu.peg.slack.pt.api.slack.model.RefinementMessage;
import nu.peg.slack.pt.api.slack.model.SlackMessage;
import nu.peg.slack.pt.api.transport.TransportApi;
import nu.peg.slack.pt.api.transport.model.Location;
import nu.peg.slack.pt.model.ConnectionRequest;
import nu.peg.slack.pt.model.Locations;
import nu.peg.slack.pt.service.LocationService;

import java.util.List;

import javax.inject.Inject;

import static nu.peg.slack.pt.App.connectionRequestCache;

public class DefaultLocationService implements LocationService {

    @Inject
    private TransportApi transportApi;

    @Override
    public Locations queryLocations(String from, String to) {
        List<Location> fromLocations = transportApi.queryLocation(from);
        List<Location> toLocations = transportApi.queryLocation(to);

        return new Locations(from, to, fromLocations, toLocations);
    }

    @Override
    public SlackMessage makeRefinementMessage(ConnectionRequest request) {
        String fromTo = null;
        String uncertainInput = null;
        List<Location> options = null;

        Locations locations = request.getLocations();

        if (locations.getFrom() == null) {
            fromTo = "from";
            uncertainInput = locations.getOriginalFrom();
            options = locations.getFromOptions();
        } else if (locations.getTo() == null) {
            fromTo = "to";
            uncertainInput = locations.getOriginalTo();
            options = locations.getToOptions();
        }

        String callbackId = String.format("%d%d", request.hashCode(), System.currentTimeMillis());
        connectionRequestCache.put(callbackId, request);

        return new RefinementMessage(uncertainInput, fromTo, options, callbackId);
    }
}
