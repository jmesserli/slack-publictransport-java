package nu.peg.slack.pt.service.internal;

import nu.peg.slack.pt.api.slack.SlackApi;
import nu.peg.slack.pt.api.slack.model.CommandPostData;
import nu.peg.slack.pt.api.slack.model.ErrorMessage;
import nu.peg.slack.pt.api.slack.model.SlackMessage;
import nu.peg.slack.pt.api.transport.TransportApi;
import nu.peg.slack.pt.api.transport.model.Connection;
import nu.peg.slack.pt.model.ConnectionRequest;
import nu.peg.slack.pt.model.Locations;
import nu.peg.slack.pt.service.ConnectionService;
import nu.peg.slack.pt.service.LocationService;
import nu.peg.slack.pt.util.CommandParser;

import org.jvnet.hk2.annotations.Service;

import java.util.List;

import javax.inject.Inject;

@Service
public class ThreadedConnectionService implements ConnectionService {

    private TransportApi transportApi;
    private SlackApi slackApi;
    private LocationService locationService;

    @Inject
    public ThreadedConnectionService(TransportApi transportApi, SlackApi slackApi, LocationService locationService) {
        this.transportApi = transportApi;
        this.slackApi = slackApi;
        this.locationService = locationService;
    }

    @Override
    public void handleCommand(CommandPostData command) {
        ConnectionServiceRunnable runnable = new ConnectionServiceRunnable(command);
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

        private CommandPostData commandData;

        public ConnectionServiceRunnable(CommandPostData commandData) {
            this.commandData = commandData;
        }

        private void sendError(String error) {
            slackApi.sendResponse(commandData.getResponseUrl(), new ErrorMessage(error));
        }

        @Override
        public void run() {
            List<String> args = CommandParser.getArguments(commandData.getText());

            if (args.size() < 2) {
                sendError("Es muss mindestens eine Start- und Endstation eingegeben werden.\nBeispiel: _/connections Bern Oberzollikofen_");
                return;
            }

            Locations locations = locationService.queryLocations(args.get(0), args.get(1));
            if (!locations.isUnique()) {
                SlackMessage refinementMessage = locationService.makeRefinementMessage(locations);
                slackApi.sendResponse(commandData.getResponseUrl(), refinementMessage);
                return;
            }
        }
    }
}
