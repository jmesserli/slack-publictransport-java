package nu.peg.slack.pt.api.slack.model;

import com.google.gson.annotations.SerializedName;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.ws.rs.FormParam;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandPostData {

    /* @SerializedName and @JsonProperty are for Gson and Jackson respectively */

    @FormParam("token")
    private String token;

    @FormParam("team_id")
    @SerializedName("team_id")
    @JsonProperty("team_id")
    private String teamId;

    @FormParam("team_domain")
    @SerializedName("team_domain")
    @JsonProperty("team_domain")
    private String teamDomain;

    @FormParam("channel_id")
    @SerializedName("channel_id")
    @JsonProperty("channel_id")
    private String channelId;

    @FormParam("channel_name")
    @SerializedName("channel_name")
    @JsonProperty("channel_name")
    private String channelName;

    @FormParam("user_id")
    @SerializedName("user_id")
    @JsonProperty("user_id")
    private String userId;

    @FormParam("user_name")
    @SerializedName("user_name")
    @JsonProperty("user_name")
    private String userName;

    @FormParam("command")
    private String command;

    @FormParam("text")
    private String text;

    @FormParam("response_url")
    @SerializedName("response_url")
    @JsonProperty("response_url")
    private String responseUrl;
}
