package nu.peg.slack.pt.di.factory;

import nu.peg.slack.pt.api.slack.DevelopmentLoggingSlackApi;
import nu.peg.slack.pt.api.slack.SlackApi;

import org.glassfish.hk2.api.Factory;

public class DevelopmentSlackApiFactory implements Factory<SlackApi> {

    @Override
    public SlackApi provide() {
        return new DevelopmentLoggingSlackApi();
    }

    @Override
    public void dispose(SlackApi instance) {
    }
}
