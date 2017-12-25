package nu.peg.slack.pt.config

import nu.peg.slack.pt.di.binder.SlackBinder

import org.glassfish.jersey.server.ResourceConfig

class ApplicationConfig : ResourceConfig() {
    init {
        // Endpoints / Components
        packages(true, "nu.peg.slack")

        // Dependency Injection module
        register(SlackBinder())
    }
}
