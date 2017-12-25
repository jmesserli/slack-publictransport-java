package nu.peg.slack.pt.api.slack.model

import com.google.gson.annotations.SerializedName

import lombok.AllArgsConstructor
import lombok.Data

@Data
@AllArgsConstructor
class AttachmentField {
    private val title: String? = null
    private val value: String? = null
    @SerializedName("short")
    private val isShort: Boolean = false
}
