package nu.peg.slack.pt.di.factory;

import nu.peg.slack.pt.api.transport.TransportApi;
import nu.peg.slack.pt.service.ConnectionService;

import org.glassfish.hk2.api.Factory;

import javax.inject.Inject;

public class ConnectionServiceFactory implements Factory<ConnectionService> {

    @Inject
    private TransportApi api;

    @Override
    public ConnectionService provide() {
        return new ConnectionService(api);
    }

    @Override
    public void dispose(ConnectionService connectionService) {
    }
}
