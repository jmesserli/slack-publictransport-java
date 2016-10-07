package nu.peg.slack.pt.di.factory;

import nu.peg.slack.pt.api.slack.SlackApi;
import nu.peg.slack.pt.service.*;
import nu.peg.slack.pt.service.internal.ThreadedInteractiveService;

import org.glassfish.hk2.api.Factory;

import javax.inject.Inject;

public class InteractiveServiceFactory implements Factory<InteractiveService> {

    private ConnectionService connectionService;
    private LocationService locationService;
    private SlackApi slackApi;

    @Inject
    public InteractiveServiceFactory(ConnectionService connectionService, LocationService locationService, SlackApi slackApi) {
        this.connectionService = connectionService;
        this.locationService = locationService;
        this.slackApi = slackApi;
    }

    @Override
    public InteractiveService provide() {
        return new ThreadedInteractiveService(connectionService, locationService, slackApi);
    }

    @Override
    public void dispose(InteractiveService interactiveService) {
    }
}
