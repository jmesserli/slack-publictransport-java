package nu.peg.slack.pt.di.binder

import nu.peg.slack.pt.api.slack.SlackApi
import nu.peg.slack.pt.api.transport.TransportApi
import nu.peg.slack.pt.di.factory.*
import nu.peg.slack.pt.service.*

import org.glassfish.hk2.utilities.binding.AbstractBinder

class SlackBinder : AbstractBinder() {

    protected fun configure() {
        val devMode = tryParseBoolean((System.getProperties() as java.util.Map).getOrDefault("developmentMode", null))

        if (devMode) {
            bindFactory(DevelopmentSlackApiFactory::class.java).to(SlackApi::class.java)
        } else {
            bindFactory(SlackApiFactory::class.java).to(SlackApi::class.java)
        }
        bindFactory(TransportApiFactory::class.java).to(TransportApi::class.java)
        bindFactory(OauthServiceFactory::class.java).to(OauthService::class.java)
        bindFactory(ConnectionServiceFactory::class.java).to(ConnectionService::class.java)
        bindFactory(LocationServiceFactory::class.java).to(LocationService::class.java)
        bindFactory(InteractiveServiceFactory::class.java).to(InteractiveService::class.java)
    }

    private fun tryParseBoolean(obj: Any?): Boolean {
        if (null == obj || obj !is String) return false

        val booleanString = obj.toLowerCase()
        // returns false if it fails to parse
        return java.lang.Boolean.parseBoolean(booleanString)
    }
}
