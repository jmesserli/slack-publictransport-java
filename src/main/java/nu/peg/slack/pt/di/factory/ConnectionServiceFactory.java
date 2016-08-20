package nu.peg.slack.pt.di.factory;

import nu.peg.slack.pt.service.ConnectionService;

import org.glassfish.hk2.api.Factory;

public class ConnectionServiceFactory implements Factory<ConnectionService> {

    @Override
    public ConnectionService provide() {
        return new ConnectionService();
    }

    @Override
    public void dispose(ConnectionService connectionService) {
    }
}
