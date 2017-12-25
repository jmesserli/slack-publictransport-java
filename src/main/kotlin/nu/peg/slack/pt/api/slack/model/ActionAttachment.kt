package nu.peg.slack.pt.api.slack.model

import com.google.gson.annotations.SerializedName

import com.fasterxml.jackson.annotation.JsonProperty

import lombok.Data

@Data
class ActionAttachment : Attachment {

    private val title: String? = null

    private val text: String? = null

    @SerializedName("callback_id")
    @JsonProperty("callback_id")
    private val callbackId: String? = null

    private val color: String? = null

    private val actions: List<Action>? = null

}
