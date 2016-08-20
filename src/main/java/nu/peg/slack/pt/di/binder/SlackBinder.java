package nu.peg.slack.pt.di.binder;

import nu.peg.slack.pt.di.factory.OauthServiceFactory;
import nu.peg.slack.pt.service.OauthService;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class SlackBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bindFactory(OauthServiceFactory.class).to(OauthService.class);
    }
}
