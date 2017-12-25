package nu.peg.slack.pt.api.slack.model

import nu.peg.slack.pt.api.transport.model.Connection
import nu.peg.slack.pt.api.transport.model.Section
import nu.peg.slack.pt.util.Util

import java.util.*

import lombok.Data
import lombok.EqualsAndHashCode

@Data
@EqualsAndHashCode(callSuper = true)
class ConnectionDetailMessage(connection: Connection) : SlackMessage() {
    @Transient private val colors = Arrays.asList("#FFA05D", "#FF6550", "#FFC550", "#E8A249", "#E87349")
    @Transient private var colorIdx = 0

    init {
        val startSection = connection.getSections().get(0)
        val endSection = connection.getSections().get(connection.getSections().size() - 1)
        setText(String.format("Ihre Verbindung von %s nach %s:", startSection.getDeparture().getStation().getName(), endSection.getArrival().getStation().getName()))

        setAttachments(ArrayList<E>())
        for (section in connection.getSections()) {
            if (section.getWalk() != null) {
                getAttachments().add(makeWalkAttachment(section))
            } else {
                getAttachments().addAll(makeVehicleAttachments(section))
            }
        }
    }

    private fun makeWalkAttachment(section: Section): Attachment {
        val fields = ArrayList<AttachmentField>()
        fields.add(AttachmentField("Von", section.getDeparture().getStation().getName(), false))
        fields.add(AttachmentField("Nach", section.getArrival().getStation().getName(), true))
        fields.add(AttachmentField("Dauer", section.getWalk().getDuration(), true))

        return ConnectionDetailAttachment("Fussweg", nextColor(), fields)
    }

    private fun makeVehicleAttachments(section: Section): List<Attachment> {
        val color = nextColor()

        val departureFields = ArrayList<AttachmentField>()
        val hasDeparturePerron = section.getDeparture().getPlatform() != null && !section.getDeparture().getPlatform().trim().isEmpty()
        departureFields.add(AttachmentField("Von", section.getDeparture().getStation().getName(), !hasDeparturePerron))
        if (hasDeparturePerron)
            departureFields.add(AttachmentField("Perron", section.getDeparture().getPlatform(), hasDeparturePerron))
        departureFields.add(AttachmentField("Abfahrt", Util.transportToSimpleTime(section.getDeparture().getDeparture()), true))

        val departureAttachment = ConnectionDetailAttachment(String.format("%s in Richtung %s", section.getJourney().getCategory(), section.getJourney().getTo()), color, departureFields)

        val arrivalFields = ArrayList<AttachmentField>()
        val hasArrivalPerron = section.getArrival().getPlatform() != null && !section.getArrival().getPlatform().trim().isEmpty()
        arrivalFields.add(AttachmentField("Nach", section.getArrival().getStation().getName(), !hasArrivalPerron))
        if (hasArrivalPerron)
            arrivalFields.add(AttachmentField("Perron", section.getArrival().getPlatform(), hasArrivalPerron))
        arrivalFields.add(AttachmentField("Ankunft", Util.transportToSimpleTime(section.getArrival().getArrival()), true))

        val arrivalAttachment = ConnectionDetailAttachment(null, color, arrivalFields)

        return Arrays.asList<Attachment>(departureAttachment, arrivalAttachment)
    }

    private fun nextColor(): String {
        colorIdx = (colorIdx + 1) % (colors.size + 1)
        return colors[colorIdx]
    }
}
