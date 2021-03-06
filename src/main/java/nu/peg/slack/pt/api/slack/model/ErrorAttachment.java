package nu.peg.slack.pt.api.slack.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorAttachment implements Attachment {

    private String color, text;

    @SerializedName("mrkdwn_in")
    private List<String> markdownIn;

}
