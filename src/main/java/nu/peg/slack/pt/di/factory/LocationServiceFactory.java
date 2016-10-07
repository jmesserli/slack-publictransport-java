package nu.peg.slack.pt.di.factory;


import nu.peg.slack.pt.api.transport.TransportApi;
import nu.peg.slack.pt.service.LocationService;
import nu.peg.slack.pt.service.internal.DefaultLocationService;

import org.glassfish.hk2.api.Factory;

import javax.inject.Inject;

public class LocationServiceFactory implements Factory<LocationService> {

    private TransportApi transportApi;

    @Inject
    public LocationServiceFactory(TransportApi transportApi) {
        this.transportApi = transportApi;
    }

    @Override
    public LocationService provide() {
        return new DefaultLocationService(transportApi);
    }

    @Override
    public void dispose(LocationService instance) {

    }
}
