package nu.peg.slack.pt.service.internal

import nu.peg.slack.pt.api.slack.SlackApi
import nu.peg.slack.pt.api.slack.model.*
import nu.peg.slack.pt.api.transport.model.Connection
import nu.peg.slack.pt.model.ConnectionRequest
import nu.peg.slack.pt.service.*
import java.util.concurrent.ExecutionException

import javax.inject.Inject

import nu.peg.slack.pt.App.*

class ThreadedInteractiveService @Inject
constructor(private val connectionService: ConnectionService, private val locationService: LocationService, private val slackApi: SlackApi) : InteractiveService {

    override fun handleInteractive(interactiveData: InteractivePostData) {
        Thread(InteractiveServiceThread(interactiveData)).start()
    }

    private inner class InteractiveServiceThread internal constructor(private val interactiveData: InteractivePostData) : Runnable {

        private fun handleLocationRefinement(callbackId: String, actionValue: String) {
            val request: ConnectionRequest
            try {
                request = connectionRequestCache.get(callbackId) { null }
            } catch (e: ExecutionException) {
                throw RuntimeException(e)
            }

            // TODO: 28.08.2016 Handle null values from cache with error message

            val split = actionValue.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val fromTo = split[0]
            val idx = split[1]

            request.getLocations().choose(fromTo, Integer.parseInt(idx))

            if (!request.getLocations().isUnique()) {
                val refinementMessage = locationService.makeRefinementMessage(request)
                slackApi.sendResponse(interactiveData.getPayloadObject().getResponseUrl(), refinementMessage)
                return
            }

            val overviewMessage = connectionService.makeConnectionOverview(request)
            slackApi.sendResponse(interactiveData.getPayloadObject().getResponseUrl(), overviewMessage)
        }

        private fun handleOverviewSelection(callbackId: String, actionValue: String) {
            val connections: List<Connection>
            try {
                connections = connectionsCache.get(callbackId) { null }
            } catch (e: ExecutionException) {
                throw RuntimeException(e)
            }

            // TODO: 28.08.2016 Handle null values from cache with error message

            val connectionIdx = Integer.parseInt(actionValue)
            val connection = connections[connectionIdx]

            val connectionDetailMessage = connectionService.makeConnectionDetail(connection)
            slackApi.sendResponse(interactiveData.getPayloadObject().getResponseUrl(), connectionDetailMessage)
        }

        override fun run() {
            val payload = interactiveData.getPayloadObject()
            val callbackId = payload.getCallbackId()
            val action = payload.getActions().stream().findFirst().orElse(null)
            val name = action.getName()
            val actionValue = action.getValue()

            if (name == ACTION_LOCATION_REFINE_NAME) {
                handleLocationRefinement(callbackId, actionValue)
            } else if (name == ACTION_OVERVIEW_SELECT_NAME) {
                handleOverviewSelection(callbackId, actionValue)
            }
        }
    }
}
