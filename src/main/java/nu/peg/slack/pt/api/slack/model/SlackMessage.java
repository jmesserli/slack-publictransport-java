package nu.peg.slack.pt.api.slack.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class SlackMessage {

    private String text;
    private List<Attachment> attachments;

}
