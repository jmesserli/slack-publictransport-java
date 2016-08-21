package nu.peg.slack.pt.api.slack.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class InteractivePayload {

    private List<Action> actions;

    @SerializedName("callback_id")
    private String callbackId;

    private Team team;
    private Channel channel;
    private User user;

    @SerializedName("action_ts")
    private String actionTimestamp;

    @SerializedName("message_ts")
    private String messageTimestamp;

    @SerializedName("attachment_id")
    private String attachmentId;

    private String token;

    @SerializedName("original_message")
    private String originalMessage;

    @SerializedName("response_url")
    private String responseUrl;

    @Data
    public class Action {
        private String name, value;
    }

    @Data
    public class Team {
        private String id, domain;
    }

    @Data
    public class Channel {
        private String id, name;
    }

    @Data
    public class User {
        private String id, name;
    }
}
