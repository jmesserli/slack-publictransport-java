package nu.peg.slack.pt.api.slack.model

import lombok.*

@Data
@AllArgsConstructor
@EqualsAndHashCode
class ConnectionDetailAttachment : Attachment {
    private val pretext: String? = null
    private val color: String? = null
    private val fields: List<AttachmentField>? = null
}
