package nu.peg.slack.pt.di.factory;

import nu.peg.slack.pt.api.transport.TransportApi;
import org.glassfish.hk2.api.Factory;

public class TransportApiFactory implements Factory<TransportApi> {
    @Override
    public TransportApi provide() {
        return new TransportApi();
    }

    @Override
    public void dispose(TransportApi instance) {

    }
}
