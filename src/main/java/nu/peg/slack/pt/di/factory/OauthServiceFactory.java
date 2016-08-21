package nu.peg.slack.pt.di.factory;

import nu.peg.slack.pt.service.internal.DefaultOauthService;
import nu.peg.slack.pt.service.OauthService;

import org.glassfish.hk2.api.Factory;

import static nu.peg.slack.pt.App.config;

public class OauthServiceFactory implements Factory<OauthService> {

    @Override
    public OauthService provide() {
        String clientId = config.getProperty("slack.client.id");
        String clientSecret = config.getProperty("slack.client.secret");

        return new DefaultOauthService(clientId, clientSecret);
    }

    @Override
    public void dispose(OauthService oauthService) {
    }
}
