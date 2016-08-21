package nu.peg.slack.pt.service.internal;

import nu.peg.slack.pt.api.slack.model.CommandPostData;
import nu.peg.slack.pt.api.slack.model.SlackMessage;
import nu.peg.slack.pt.api.transport.TransportApi;
import nu.peg.slack.pt.api.transport.model.Connection;
import nu.peg.slack.pt.model.ConnectionRequest;
import nu.peg.slack.pt.service.ConnectionService;

import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;

@Service
public class ThreadedConnectionService implements ConnectionService {

    private TransportApi transportApi;

    @Inject
    public ThreadedConnectionService(TransportApi transportApi) {
        this.transportApi = transportApi;
    }

    @Override
    public void handleCommand(CommandPostData command) {
        ConnectionServiceRunnable runnable = new ConnectionServiceRunnable(this, command);
        new Thread(runnable).start();
    }

    @Override
    public SlackMessage makeConnectionOverview(ConnectionRequest request) {
        return null;
    }

    @Override
    public SlackMessage makeConnectionDetail(Connection connection) {
        return null;
    }

    private class ConnectionServiceRunnable implements Runnable {

        private ThreadedConnectionService service;
        private CommandPostData commandData;

        public ConnectionServiceRunnable(ThreadedConnectionService service, CommandPostData commandData) {
            this.service = service;
            this.commandData = commandData;
        }

        @Override
        public void run() {

        }
    }
}
