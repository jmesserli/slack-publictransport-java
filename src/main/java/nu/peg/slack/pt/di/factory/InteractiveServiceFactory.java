package nu.peg.slack.pt.di.factory;

import nu.peg.slack.pt.api.slack.SlackApi;
import nu.peg.slack.pt.api.transport.TransportApi;
import nu.peg.slack.pt.service.ConnectionService;
import nu.peg.slack.pt.service.InteractiveService;
import nu.peg.slack.pt.service.LocationService;
import nu.peg.slack.pt.service.internal.ThreadedInteractiveService;
import org.glassfish.hk2.api.Factory;

import javax.inject.Inject;

public class InteractiveServiceFactory implements Factory<InteractiveService> {

    private ConnectionService connectionService;
    private LocationService locationService;
    private SlackApi slackApi;
    private TransportApi transportApi;

    @Inject
    public InteractiveServiceFactory(ConnectionService connectionService, LocationService locationService, SlackApi slackApi, TransportApi transportApi) {
        this.connectionService = connectionService;
        this.locationService = locationService;
        this.slackApi = slackApi;
        this.transportApi = transportApi;
    }

    @Override
    public InteractiveService provide() {
        return new ThreadedInteractiveService(connectionService, locationService, slackApi, transportApi);
    }

    @Override
    public void dispose(InteractiveService interactiveService) {
    }
}
