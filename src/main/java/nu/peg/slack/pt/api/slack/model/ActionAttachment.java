package nu.peg.slack.pt.api.slack.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class ActionAttachment implements Attachment {

    private String title;

    private String text;

    @SerializedName("callback_id")
    @JsonProperty("callback_id")
    private String callbackId;

    private String color;

    private List<Action> actions;

}
