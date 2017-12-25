package nu.peg.slack.pt.api.slack.model

import nu.peg.slack.pt.api.transport.model.Location

import java.util.*

import nu.peg.slack.pt.App.ACTION_LOCATION_REFINE_NAME

class RefinementMessage(uncertainLocation: String, fromTo: String, locations: List<Location>, callbackId: String) : SlackMessage() {

    init {
        setText(String.format("Für _%s_ gibt es mehrere Stationen", uncertainLocation))

        val actions = ArrayList<Action>()
        for (i in locations.indices) {
            val action = Action()
            action.setText(locations[i].getName())
            action.setStyle(if (i == 0) ActionButtonStyle.Primary else ActionButtonStyle.Default)
            action.setName(ACTION_LOCATION_REFINE_NAME)
            action.setValue(fromTo + ":" + i)

            actions.add(action)
        }

        val attachment = ActionAttachment()
        attachment.setText("Bitte wähle die gewünschte Station")
        attachment.setColor("#000000")
        attachment.setActions(actions)
        attachment.setCallbackId(callbackId)

        setAttachments(listOf<T>(attachment))
    }
}
