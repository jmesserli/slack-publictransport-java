package nu.peg.slack.pt.api.transport.model

import lombok.Data

@Data
class Location {

    private val id: String? = null
    private val name: String? = null
    private val score: Int? = null
    private val coordinates: Coordinates? = null
    private val distance: Double? = null

}
