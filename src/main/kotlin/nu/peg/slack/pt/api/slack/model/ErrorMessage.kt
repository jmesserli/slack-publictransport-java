package nu.peg.slack.pt.api.slack.model

import java.util.Arrays

class ErrorMessage(error: String) : SlackMessage() {

    init {
        setAttachments(Arrays.asList<T>(
                ErrorAttachment("#ff0000", String.format("*Error*: %s", error), Arrays.asList<T>("text"))
        ))
    }
}
