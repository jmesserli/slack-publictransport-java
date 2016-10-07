package nu.peg.slack.pt.di.binder;

import nu.peg.slack.pt.api.slack.SlackApi;
import nu.peg.slack.pt.api.transport.TransportApi;
import nu.peg.slack.pt.di.factory.*;
import nu.peg.slack.pt.service.*;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class SlackBinder extends AbstractBinder {

    @Override
    protected void configure() {
        boolean devMode = tryParseBoolean(System.getProperties().getOrDefault("developmentMode", null));

        if (devMode) {
            bindFactory(DevelopmentSlackApiFactory.class).to(SlackApi.class);
        } else {
            bindFactory(SlackApiFactory.class).to(SlackApi.class);
        }
        bindFactory(TransportApiFactory.class).to(TransportApi.class);
        bindFactory(OauthServiceFactory.class).to(OauthService.class);
        bindFactory(ConnectionServiceFactory.class).to(ConnectionService.class);
        bindFactory(LocationServiceFactory.class).to(LocationService.class);
        bindFactory(InteractiveServiceFactory.class).to(InteractiveService.class);
    }

    private boolean tryParseBoolean(Object obj) {
        if (null == obj || !(obj instanceof String)) return false;

        String booleanString = ((String) obj).toLowerCase();
        // returns false if it fails to parse
        return Boolean.parseBoolean(booleanString);
    }
}
