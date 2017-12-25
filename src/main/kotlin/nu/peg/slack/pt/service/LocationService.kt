package nu.peg.slack.pt.service

import nu.peg.slack.pt.api.slack.model.SlackMessage
import nu.peg.slack.pt.model.ConnectionRequest
import nu.peg.slack.pt.model.Locations

interface LocationService {

    fun queryLocations(from: String, to: String): Locations

    fun makeRefinementMessage(locations: ConnectionRequest): SlackMessage
}
