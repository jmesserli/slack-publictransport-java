package nu.peg.slack.pt.di.factory

import nu.peg.slack.pt.service.OauthService
import nu.peg.slack.pt.service.internal.DefaultOauthService

import org.glassfish.hk2.api.Factory

import nu.peg.slack.pt.App.config

class OauthServiceFactory : Factory<OauthService> {

    fun provide(): OauthService {
        val clientId = config.getProperty("slack.client.id")
        val clientSecret = config.getProperty("slack.client.secret")

        return DefaultOauthService(clientId, clientSecret)
    }

    fun dispose(oauthService: OauthService) {}
}
