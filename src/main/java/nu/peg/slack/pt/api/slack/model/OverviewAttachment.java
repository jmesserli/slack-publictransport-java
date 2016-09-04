package nu.peg.slack.pt.api.slack.model;

import com.google.gson.annotations.SerializedName;

import nu.peg.slack.pt.api.transport.model.Connection;

import java.util.ArrayList;
import java.util.List;

import lombok.*;

@Data
@EqualsAndHashCode
public class OverviewAttachment implements Attachment {

    private final String color = "#000";
    private List<OverviewAttachmentField> fields;

    public OverviewAttachment(Connection connection) {
        fields = new ArrayList<>();

        fields.add(new OverviewAttachmentField("Abfahrt", connection.getFrom().getDeparture(), true));
        fields.add(new OverviewAttachmentField("Ankunft", connection.getTo().getArrival(), true));
        fields.add(new OverviewAttachmentField("Linie", connection.getSections().get(0).getJourney().getName(), true));
        fields.add(new OverviewAttachmentField("Dauer", connection.getDuration(), true));
    }

    @Data
    @AllArgsConstructor
    private class OverviewAttachmentField {
        private String title, value;
        @SerializedName("short")
        private boolean isShort;
    }
}
