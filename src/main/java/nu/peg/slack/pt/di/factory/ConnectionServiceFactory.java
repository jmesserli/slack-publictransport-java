package nu.peg.slack.pt.di.factory;

import nu.peg.slack.pt.api.slack.SlackApi;
import nu.peg.slack.pt.api.transport.TransportApi;
import nu.peg.slack.pt.service.ConnectionService;
import nu.peg.slack.pt.service.LocationService;
import nu.peg.slack.pt.service.internal.ThreadedConnectionService;
import org.glassfish.hk2.api.Factory;

import javax.inject.Inject;

public class ConnectionServiceFactory implements Factory<ConnectionService> {

    private TransportApi transportApi;
    private SlackApi slackApi;
    private LocationService locationService;

    @Inject
    public ConnectionServiceFactory(TransportApi transportApi, SlackApi slackApi, LocationService locationService) {
        this.transportApi = transportApi;
        this.slackApi = slackApi;
        this.locationService = locationService;
    }

    @Override
    public ConnectionService provide() {
        return new ThreadedConnectionService(transportApi, slackApi, locationService);
    }

    @Override
    public void dispose(ConnectionService connectionService) {
    }
}
