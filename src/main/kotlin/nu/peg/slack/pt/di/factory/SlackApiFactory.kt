package nu.peg.slack.pt.di.factory

import nu.peg.slack.pt.api.slack.SlackApi

import org.glassfish.hk2.api.Factory

class SlackApiFactory : Factory<SlackApi> {

    fun provide(): SlackApi {
        return SlackApi()
    }

    fun dispose(instance: SlackApi) {

    }
}
