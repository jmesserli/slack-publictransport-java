package nu.peg.slack.pt.api.slack.model

import com.google.gson.annotations.SerializedName

import lombok.*

@Data
@NoArgsConstructor
@AllArgsConstructor
class ErrorAttachment : Attachment {

    private val color: String? = null
    private val text: String? = null

    @SerializedName("mrkdwn_in")
    private val markdownIn: List<String>? = null

}
