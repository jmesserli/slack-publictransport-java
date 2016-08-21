package nu.peg.slack.pt.api.slack.model;

import java.util.Arrays;

public class ErrorMessage extends SlackMessage {

    public ErrorMessage(String error) {
        setAttachments(Arrays.asList(
                new ErrorAttachment("#ff0000", String.format("*Error*: %s", error), Arrays.asList("text"))
        ));
    }
}
