package nu.peg.slack.pt.api.slack.model

import com.google.gson.annotations.SerializedName

import com.fasterxml.jackson.annotation.JsonValue

enum class ActionButtonStyle {

    @SerializedName("default")
    Default,

    @SerializedName("primary")
    Primary,

    @SerializedName("danger")
    Danger;

    val serialized: String
        @JsonValue
        get() = this.name.toLowerCase()
}
