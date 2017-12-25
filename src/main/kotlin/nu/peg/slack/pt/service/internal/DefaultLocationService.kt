package nu.peg.slack.pt.service.internal

import nu.peg.slack.pt.api.slack.model.RefinementMessage
import nu.peg.slack.pt.api.slack.model.SlackMessage
import nu.peg.slack.pt.api.transport.TransportApi
import nu.peg.slack.pt.api.transport.model.Location
import nu.peg.slack.pt.model.ConnectionRequest
import nu.peg.slack.pt.model.Locations
import nu.peg.slack.pt.service.LocationService
import nu.peg.slack.pt.util.Util

import javax.inject.Inject

import nu.peg.slack.pt.App.connectionRequestCache

class DefaultLocationService @Inject
constructor(private val transportApi: TransportApi) : LocationService {

    override fun queryLocations(from: String, to: String): Locations {
        val fromLocations = transportApi.queryLocation(from)
        val toLocations = transportApi.queryLocation(to)

        return Locations(from, to, fromLocations!!, toLocations)
    }

    override fun makeRefinementMessage(request: ConnectionRequest): SlackMessage {
        var fromTo: String? = null
        var uncertainInput: String? = null
        var options: List<Location>? = null

        val locations = request.getLocations()

        if (locations.getFrom() == null) {
            fromTo = "from"
            uncertainInput = locations.getOriginalFrom()
            options = locations.getFromOptions()
        } else if (locations.getTo() == null) {
            fromTo = "to"
            uncertainInput = locations.getOriginalTo()
            options = locations.getToOptions()
        }

        val callbackId = Util.makeCallbackId(request)
        connectionRequestCache.put(callbackId, request)

        return RefinementMessage(uncertainInput, fromTo, options!!, callbackId)
    }
}
