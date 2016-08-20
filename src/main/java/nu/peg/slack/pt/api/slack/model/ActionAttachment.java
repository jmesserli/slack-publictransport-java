package nu.peg.slack.pt.api.slack.model;

import com.google.gson.annotations.SerializedName;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.Data;

@Data
public class ActionAttachment implements Attachment {

    private String title;

    @SerializedName("callback_id")
    @JsonProperty("callback_id")
    private String callbackId;

    private String color;

    private List<Action> actions;

}
