package nu.peg.slack.pt.config;

import nu.peg.slack.pt.di.binder.SlackBinder;
import nu.peg.slack.pt.endpoint.SlackEndpoint;

import org.glassfish.jersey.server.ResourceConfig;

public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {

        // Endpoints / Components
        register(SlackEndpoint.class);

        // Dependency Injection module
        register(new SlackBinder());
    }
}
