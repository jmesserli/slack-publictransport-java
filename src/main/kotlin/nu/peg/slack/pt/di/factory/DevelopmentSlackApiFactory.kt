package nu.peg.slack.pt.di.factory

import nu.peg.slack.pt.api.slack.DevelopmentLoggingSlackApi
import nu.peg.slack.pt.api.slack.SlackApi

import org.glassfish.hk2.api.Factory

class DevelopmentSlackApiFactory : Factory<SlackApi> {

    fun provide(): SlackApi {
        return DevelopmentLoggingSlackApi()
    }

    fun dispose(instance: SlackApi) {}
}
