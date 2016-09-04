package nu.peg.slack.pt.api.slack.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AttachmentField {
    private String title, value;
    @SerializedName("short")
    private boolean isShort;
}
