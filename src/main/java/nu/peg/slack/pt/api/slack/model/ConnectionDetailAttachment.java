package nu.peg.slack.pt.api.slack.model;

import java.util.List;

import lombok.*;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class ConnectionDetailAttachment implements Attachment {
    private String pretext, color;
    private List<AttachmentField> fields;
}
