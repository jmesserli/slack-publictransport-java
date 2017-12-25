package nu.peg.slack.pt.api.transport.model

import lombok.Data

@Data
class Checkpoint {

    private val station: Location? = null
    private val arrival: String? = null
    private val departure: String? = null
    private val platform: String? = null
    private val prognosis: Prognosis? = null

}
