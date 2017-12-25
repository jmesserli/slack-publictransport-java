package nu.peg.slack.pt.api.transport.model

import lombok.Data

@Data
class Stop {

    private val station: Location? = null
    private val name: String? = null
    private val category: String? = null
    private val number: String? = null
    private val operator: String? = null
    private val to: String? = null

}
