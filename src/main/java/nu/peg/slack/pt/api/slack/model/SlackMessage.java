package nu.peg.slack.pt.api.slack.model;

import java.util.List;

import lombok.Data;

@Data
public class SlackMessage {

    private String text;
    private List<Attachment> attachments;

}
