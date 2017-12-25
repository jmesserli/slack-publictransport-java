package nu.peg.slack.pt.di.factory


import nu.peg.slack.pt.api.transport.TransportApi
import nu.peg.slack.pt.service.LocationService
import nu.peg.slack.pt.service.internal.DefaultLocationService

import org.glassfish.hk2.api.Factory

import javax.inject.Inject

class LocationServiceFactory @Inject
constructor(private val transportApi: TransportApi) : Factory<LocationService> {

    fun provide(): LocationService {
        return DefaultLocationService(transportApi)
    }

    fun dispose(instance: LocationService) {

    }
}
