package nu.peg.slack.pt.api.slack.model;

import lombok.Data;

import java.util.List;

@Data
public class SlackMessage {

    private String text;
    private List<Attachment> attachments;

}
