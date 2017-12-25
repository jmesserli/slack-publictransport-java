package nu.peg.slack.pt.service.internal

import nu.peg.slack.pt.App
import nu.peg.slack.pt.api.slack.SlackApi
import nu.peg.slack.pt.api.slack.model.*
import nu.peg.slack.pt.api.transport.TransportApi
import nu.peg.slack.pt.api.transport.model.Connection
import nu.peg.slack.pt.model.ConnectionRequest
import nu.peg.slack.pt.model.Locations
import nu.peg.slack.pt.service.ConnectionService
import nu.peg.slack.pt.service.LocationService
import nu.peg.slack.pt.util.CommandParser
import nu.peg.slack.pt.util.Util

import org.jvnet.hk2.annotations.Service

import java.time.LocalTime
import java.util.regex.Matcher
import java.util.regex.Pattern

import javax.inject.Inject

@Service
class ThreadedConnectionService @Inject
constructor(private val transportApi: TransportApi, private val slackApi: SlackApi, private val locationService: LocationService) : ConnectionService {

    override fun handleCommand(command: CommandPostData) {
        val runnable = ConnectionServiceRunnable(command)
        Thread(runnable).start()
    }

    override fun makeConnectionOverview(request: ConnectionRequest): SlackMessage {
        val connections = transportApi.queryConnections(request)
        val message = ConnectionOverviewMessage(request, connections!!)

        val callbackId = Util.makeCallbackId(message)
        val messageAttachments = message.getAttachments()
        (messageAttachments.get(messageAttachments.size - 1) as ActionAttachment).setCallbackId(callbackId)
        App.connectionsCache.put(callbackId, connections)

        return message
    }

    override fun makeConnectionDetail(connection: Connection): SlackMessage {
        return ConnectionDetailMessage(connection)
    }

    private inner class ConnectionServiceRunnable internal constructor(private val commandData: CommandPostData) : Runnable {

        private fun sendError(error: String) {
            slackApi.sendResponse(commandData.getResponseUrl(), ErrorMessage(error))
        }

        override fun run() {
            val args = CommandParser.getArguments(commandData.getText())

            if (args.size < 2) {
                sendError("Es muss mindestens eine Start- und Endstation eingegeben werden.\nBeispiel: _/connections Bern Oberzollikofen_")
                return
            }

            var time = LocalTime.now()
            var isArrivalTime = false

            if (args.size >= 3) {
                val timePattern = Pattern.compile("([0-1]?\\d|2[0-3]):([0-5]\\d)(an|ab)")
                val matcher = timePattern.matcher(args[2])

                if (matcher.matches()) {
                    time = LocalTime.of(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)))
                    isArrivalTime = matcher.group(3) == "an"
                } else {
                    sendError("Das Format der Zeit war nicht gültig.\nBeispiele: 12:30ab, 11:09an")
                    return
                }
            }

            val locations = locationService.queryLocations(args[0], args[1])
            val request = ConnectionRequest(locations, time, isArrivalTime)
            if (!locations.isUnique) {
                val refinementMessage = locationService.makeRefinementMessage(request)
                slackApi.sendResponse(commandData.getResponseUrl(), refinementMessage)
                return
            }

            val connectionOverviewMessage = makeConnectionOverview(request)
            slackApi.sendResponse(commandData.getResponseUrl(), connectionOverviewMessage)
        }
    }
}
