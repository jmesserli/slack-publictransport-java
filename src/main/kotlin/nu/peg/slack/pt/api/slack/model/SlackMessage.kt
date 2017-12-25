package nu.peg.slack.pt.api.slack.model

import lombok.Data
import lombok.EqualsAndHashCode

@Data
@EqualsAndHashCode
open class SlackMessage {

    private val text: String? = null
    private val attachments: List<Attachment>? = null

}
