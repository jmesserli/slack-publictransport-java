package nu.peg.slack.pt.api.slack.model

import nu.peg.slack.pt.api.transport.model.Connection
import nu.peg.slack.pt.util.Util

import java.util.ArrayList

import lombok.Data
import lombok.EqualsAndHashCode

@Data
@EqualsAndHashCode
class OverviewAttachment(connection: Connection) : Attachment {

    private val color = "#000"
    private val fields: MutableList<AttachmentField>

    init {
        fields = ArrayList()

        fields.add(AttachmentField("Abfahrt", Util.transportToSimpleTime(connection.getFrom().getDeparture()), true))
        fields.add(AttachmentField("Ankunft", Util.transportToSimpleTime(connection.getTo().getArrival()), true))
        fields.add(AttachmentField("Linie", connection.getSections().get(0).getJourney().getName(), true))
        fields.add(AttachmentField("Dauer", connection.getDuration(), true))
    }
}
