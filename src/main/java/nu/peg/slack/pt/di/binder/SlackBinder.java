package nu.peg.slack.pt.di.binder;

import nu.peg.slack.pt.api.slack.SlackApi;
import nu.peg.slack.pt.api.transport.TransportApi;
import nu.peg.slack.pt.di.factory.*;
import nu.peg.slack.pt.service.*;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class SlackBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bindFactory(SlackApiFactory.class).to(SlackApi.class);
        bindFactory(TransportApiFactory.class).to(TransportApi.class);
        bindFactory(OauthServiceFactory.class).to(OauthService.class);
        bindFactory(ConnectionServiceFactory.class).to(ConnectionService.class);
        bindFactory(LocationServiceFactory.class).to(LocationService.class);
        bindFactory(InteractiveServiceFactory.class).to(InteractiveService.class);
    }
}
