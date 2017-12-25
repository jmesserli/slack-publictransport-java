package nu.peg.slack.pt.api.slack.model

import com.google.gson.annotations.SerializedName

import com.fasterxml.jackson.annotation.JsonProperty

import javax.ws.rs.FormParam

import lombok.*

@Data
@NoArgsConstructor
@AllArgsConstructor
class CommandPostData {

    /* @SerializedName and @JsonProperty are for Gson and Jackson respectively */

    @FormParam("token")
    private val token: String? = null

    @FormParam("team_id")
    @SerializedName("team_id")
    @JsonProperty("team_id")
    private val teamId: String? = null

    @FormParam("team_domain")
    @SerializedName("team_domain")
    @JsonProperty("team_domain")
    private val teamDomain: String? = null

    @FormParam("channel_id")
    @SerializedName("channel_id")
    @JsonProperty("channel_id")
    private val channelId: String? = null

    @FormParam("channel_name")
    @SerializedName("channel_name")
    @JsonProperty("channel_name")
    private val channelName: String? = null

    @FormParam("user_id")
    @SerializedName("user_id")
    @JsonProperty("user_id")
    private val userId: String? = null

    @FormParam("user_name")
    @SerializedName("user_name")
    @JsonProperty("user_name")
    private val userName: String? = null

    @FormParam("command")
    private val command: String? = null

    @FormParam("text")
    private val text: String? = null

    @FormParam("response_url")
    @SerializedName("response_url")
    @JsonProperty("response_url")
    private val responseUrl: String? = null
}
