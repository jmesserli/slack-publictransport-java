package nu.peg.slack.pt.api.slack.model;

import nu.peg.slack.pt.api.transport.model.Connection;
import nu.peg.slack.pt.api.transport.model.Section;

import java.util.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ConnectionDetailMessage extends SlackMessage {
    private transient List<String> colors = Arrays.asList("");
    private transient int colorIdx = 0;

    public ConnectionDetailMessage(Connection connection) {
        Section startSection = connection.getSections().get(0);
        Section endSection = connection.getSections().get(connection.getSections().size() - 1);
        setText(String.format("Ihre Verbindung von %s nach %s:", startSection.getDeparture().getStation().getName(), endSection.getArrival().getStation().getName()));

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
        departureFields.add(new AttachmentField("Von", section.getDeparture().getStation().getName(), false));
        departureFields.add(new AttachmentField("Perron", section.getDeparture().getPlatform(), true));
        departureFields.add(new AttachmentField("Abfahrt", section.getDeparture().getDeparture(), true));

        Attachment departureAttachment = new ConnectionDetailAttachment(String.format("%s in Richtung %s", section.getJourney().getCategory(), section.getJourney().getTo()), color, departureFields);

        List<AttachmentField> arrivalFields = new ArrayList<>();
        arrivalFields.add(new AttachmentField("Nach", section.getArrival().getStation().getName(), false));
        arrivalFields.add(new AttachmentField("Perron", section.getArrival().getPlatform(), true));
        arrivalFields.add(new AttachmentField("Ankunft", section.getArrival().getArrival(), true));

        Attachment arrivalAttachment = new ConnectionDetailAttachment(null, color, arrivalFields);

        return Arrays.asList(departureAttachment, arrivalAttachment);
    }

    private String nextColor() {
        colorIdx = (colorIdx + 1) % (colors.size() + 1);
        return colors.get(colorIdx);
    }
}
