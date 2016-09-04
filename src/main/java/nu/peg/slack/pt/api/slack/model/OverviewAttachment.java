package nu.peg.slack.pt.api.slack.model;

import nu.peg.slack.pt.api.transport.model.Connection;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class OverviewAttachment implements Attachment {

    private final String color = "#000";
    private List<AttachmentField> fields;

    public OverviewAttachment(Connection connection) {
        fields = new ArrayList<>();

        fields.add(new AttachmentField("Abfahrt", connection.getFrom().getDeparture(), true));
        fields.add(new AttachmentField("Ankunft", connection.getTo().getArrival(), true));
        fields.add(new AttachmentField("Linie", connection.getSections().get(0).getJourney().getName(), true));
        fields.add(new AttachmentField("Dauer", connection.getDuration(), true));
    }
}
