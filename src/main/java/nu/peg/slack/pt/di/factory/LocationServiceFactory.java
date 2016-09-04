package nu.peg.slack.pt.di.factory;


import nu.peg.slack.pt.service.LocationService;

import org.glassfish.hk2.api.Factory;

public class LocationServiceFactory implements Factory<LocationService> {

    @Override
    public LocationService provide() {
        return null;
    }

    @Override
    public void dispose(LocationService instance) {

    }
}
