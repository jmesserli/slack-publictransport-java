package nu.peg.slack.pt.api.transport.model

import lombok.Data

@Data
class LocationsResponse {

    private val stations: List<Location>? = null

}
