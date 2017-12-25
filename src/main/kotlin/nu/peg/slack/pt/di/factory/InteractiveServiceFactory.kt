package nu.peg.slack.pt.di.factory

import nu.peg.slack.pt.api.slack.SlackApi
import nu.peg.slack.pt.service.*
import nu.peg.slack.pt.service.internal.ThreadedInteractiveService

import org.glassfish.hk2.api.Factory

import javax.inject.Inject

class InteractiveServiceFactory @Inject
constructor(private val connectionService: ConnectionService, private val locationService: LocationService, private val slackApi: SlackApi) : Factory<InteractiveService> {

    fun provide(): InteractiveService {
        return ThreadedInteractiveService(connectionService, locationService, slackApi)
    }

    fun dispose(interactiveService: InteractiveService) {}
}
