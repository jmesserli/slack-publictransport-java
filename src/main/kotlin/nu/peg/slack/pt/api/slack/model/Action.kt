package nu.peg.slack.pt.api.slack.model

import lombok.Data

@Data
class Action {

    private val type = "button"
    private val name: String? = null
    private val text: String? = null
    private val style = ActionButtonStyle.Default
    private val value: String? = null

}
