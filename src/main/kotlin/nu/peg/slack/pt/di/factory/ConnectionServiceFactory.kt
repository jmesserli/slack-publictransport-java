package nu.peg.slack.pt.di.factory

import nu.peg.slack.pt.api.slack.SlackApi
import nu.peg.slack.pt.api.transport.TransportApi
import nu.peg.slack.pt.service.ConnectionService
import nu.peg.slack.pt.service.LocationService
import nu.peg.slack.pt.service.internal.ThreadedConnectionService

import org.glassfish.hk2.api.Factory

import javax.inject.Inject

class ConnectionServiceFactory @Inject
constructor(private val transportApi: TransportApi, private val slackApi: SlackApi, private val locationService: LocationService) : Factory<ConnectionService> {

    fun provide(): ConnectionService {
        return ThreadedConnectionService(transportApi, slackApi, locationService)
    }

    fun dispose(connectionService: ConnectionService) {}
}
