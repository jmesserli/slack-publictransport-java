package nu.peg.slack.pt.api.slack.model

import com.google.gson.annotations.SerializedName

import lombok.Data

@Data
class InteractivePayload {

    private val actions: List<Action>? = null

    @SerializedName("callback_id")
    private val callbackId: String? = null

    private val team: Team? = null
    private val channel: Channel? = null
    private val user: User? = null

    @SerializedName("action_ts")
    private val actionTimestamp: String? = null

    @SerializedName("message_ts")
    private val messageTimestamp: String? = null

    @SerializedName("attachment_id")
    private val attachmentId: String? = null

    private val token: String? = null

    @SerializedName("original_message")
    private val originalMessage: String? = null

    @SerializedName("response_url")
    private val responseUrl: String? = null

    @Data
    inner class Action {
        private val name: String? = null
        private val value: String? = null
    }

    @Data
    inner class Team {
        private val id: String? = null
        private val domain: String? = null
    }

    @Data
    inner class Channel {
        private val id: String? = null
        private val name: String? = null
    }

    @Data
    inner class User {
        private val id: String? = null
        private val name: String? = null
    }
}
