package nu.peg.slack.pt.api.transport.model

import lombok.Data

@Data
class Section {

    private val journey: Journey? = null
    private val walk: Walk? = null
    private val departure: Checkpoint? = null
    private val arrival: Checkpoint? = null

}
