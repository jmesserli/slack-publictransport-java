package nu.peg.slack.pt.api.slack.model;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.gson.annotations.SerializedName;

public enum ActionButtonStyle {

    @SerializedName("default")
    Default,

    @SerializedName("primary")
    Primary,

    @SerializedName("danger")
    Danger;

    @JsonValue
    public String getSerialized() {
        return this.name().toLowerCase();
    }
}
