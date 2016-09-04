package nu.peg.slack.pt.config;

import nu.peg.slack.pt.di.binder.SlackBinder;

import org.glassfish.jersey.server.ResourceConfig;

public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
        // Endpoints / Components
        packages(true, "nu.peg.slack");

        // Dependency Injection module
        register(new SlackBinder());
    }
}
