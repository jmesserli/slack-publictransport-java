package nu.peg.slack.pt.service

import nu.peg.slack.pt.api.slack.model.CommandPostData
import nu.peg.slack.pt.api.slack.model.SlackMessage
import nu.peg.slack.pt.api.transport.model.Connection
import nu.peg.slack.pt.model.ConnectionRequest

interface ConnectionService {

    fun handleCommand(commandData: CommandPostData)

    fun makeConnectionOverview(request: ConnectionRequest): SlackMessage

    fun makeConnectionDetail(connection: Connection): SlackMessage
}
