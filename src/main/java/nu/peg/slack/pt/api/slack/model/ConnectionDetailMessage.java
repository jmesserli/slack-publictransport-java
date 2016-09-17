package nu.peg.slack.pt.api.slack.model;

import nu.peg.slack.pt.api.transport.model.Connection;
import nu.peg.slack.pt.api.transport.model.Section;
import nu.peg.slack.pt.util.Util;

import java.util.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ConnectionDetailMessage extends SlackMessage {
    private transient List<String> colors = Arrays.asList("#FFA05D", "#FF6550", "#FFC550", "#E8A249", "#E87349");
    private transient int colorIdx = 0;

    public ConnectionDetailMessage(Connection connection) {
        Section startSection = connection.getSections().get(0);
        Section endSection = connection.getSections().get(connection.getSections().size() - 1);
        setText(String.format("Ihre Verbindung von %s nach %s:", startSection.getDeparture().getStation().getName(), endSection.getArrival().getStation().getName()));

        setAttachments(new ArrayList<>());
        for (Section section : connection.getSections()) {
            if (section.getWalk() != null) {
                getAttachments().add(makeWalkAttachment(section));
            } else {
                getAttachments().addAll(makeVehicleAttachments(section));
            }
        }
    }

    private Attachment makeWalkAttachment(Section section) {
        List<AttachmentField> fields = new ArrayList<>();
        fields.add(new AttachmentField("Von", section.getDeparture().getStation().getName(), false));
        fields.add(new AttachmentField("Nach", section.getArrival().getStation().getName(), true));
        fields.add(new AttachmentField("Dauer", section.getWalk().getDuration(), true));

        return new ConnectionDetailAttachment("Fussweg", nextColor(), fields);
    }

    private List<Attachment> makeVehicleAttachments(Section section) {
        String color = nextColor();

        List<AttachmentField> departureFields = new ArrayList<>();
        boolean hasDeparturePerron = section.getDeparture().getPlatform() != null && !section.getDeparture().getPlatform().trim().isEmpty();
        departureFields.add(new AttachmentField("Von", section.getDeparture().getStation().getName(), !hasDeparturePerron));
        if (hasDeparturePerron)
            departureFields.add(new AttachmentField("Perron", section.getDeparture().getPlatform(), hasDeparturePerron));
        departureFields.add(new AttachmentField("Abfahrt", Util.transportToSimpleTime(section.getDeparture().getDeparture()), true));

        Attachment departureAttachment = new ConnectionDetailAttachment(String.format("%s in Richtung %s", section.getJourney().getCategory(), section.getJourney().getTo()), color, departureFields);

        List<AttachmentField> arrivalFields = new ArrayList<>();
        boolean hasArrivalPerron = section.getArrival().getPlatform() != null && !section.getArrival().getPlatform().trim().isEmpty();
        arrivalFields.add(new AttachmentField("Nach", section.getArrival().getStation().getName(), !hasArrivalPerron));
        if (hasArrivalPerron)
            arrivalFields.add(new AttachmentField("Perron", section.getArrival().getPlatform(), hasArrivalPerron));
        arrivalFields.add(new AttachmentField("Ankunft", Util.transportToSimpleTime(section.getArrival().getArrival()), true));

        Attachment arrivalAttachment = new ConnectionDetailAttachment(null, color, arrivalFields);

        return Arrays.asList(departureAttachment, arrivalAttachment);
    }

    private String nextColor() {
        colorIdx = (colorIdx + 1) % (colors.size() + 1);
        return colors.get(colorIdx);
    }
}
