package nu.peg.slack.pt.api.slack.model

import nu.peg.slack.pt.api.transport.model.Connection
import nu.peg.slack.pt.model.ConnectionRequest

import java.util.ArrayList

import lombok.Data
import lombok.EqualsAndHashCode

import nu.peg.slack.pt.App.ACTION_OVERVIEW_SELECT_NAME

@Data
@EqualsAndHashCode(callSuper = true)
class ConnectionOverviewMessage(connectionRequest: ConnectionRequest, connections: List<Connection>) : SlackMessage() {
    init {
        setText(String.format("Ihre Verbindungen von *%s nach %s*:", connectionRequest.getLocations().getFrom().getName(), connectionRequest.getLocations().getTo().getName()))
        setAttachments(ArrayList<E>())
        connections.stream().map<OverviewAttachment>(Function<Connection, OverviewAttachment> { OverviewAttachment(it) }).forEach(Consumer<OverviewAttachment> { getAttachments().add() })

        val actions = ArrayList<Action>()
        for (i in connections.indices) {
            val action = Action()
            action.setText(String.format("Verbindung %d", i + 1))
            action.setStyle(if (i == 0) ActionButtonStyle.Primary else ActionButtonStyle.Default)
            action.setName(ACTION_OVERVIEW_SELECT_NAME)
            action.setValue(i.toString())

            actions.add(action)
        }

        val actionAttachment = ActionAttachment()
        actionAttachment.setText("Bitte wähle die gewünschte Verbingung:")
        actionAttachment.setColor("#fff")
        actionAttachment.setActions(actions)

        getAttachments().add(actionAttachment)
    }
}
