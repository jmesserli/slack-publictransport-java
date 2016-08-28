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

import javax.inject.Inject;
import java.time.LocalTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        // Todo save connections to cache
        return null;
    }

    @Override
    public SlackMessage makeConnectionDetail(Connection connection) {
        return null;
    }

    private class ConnectionServiceRunnable implements Runnable {

        private CommandPostData commandData;

        ConnectionServiceRunnable(CommandPostData commandData) {
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

            LocalTime time = LocalTime.now();
            boolean isArrivalTime = false;

            if (args.size() >= 3) {
                Pattern timePattern = Pattern.compile("([0-1]?\\d|2[0-3]):([0-5]\\d)(an|ab)");
                Matcher matcher = timePattern.matcher(args.get(2));

                if (matcher.matches()) {
                    time = LocalTime.of(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
                    isArrivalTime = matcher.group(3).equals("an");
                } else {
                    sendError("Das Format der Zeit war nicht gültig.\nBeispiele: 12:30ab, 11:09an");
                    return;
                }
            }

            Locations locations = locationService.queryLocations(args.get(0), args.get(1));
            if (!locations.isUnique()) {
                SlackMessage refinementMessage = locationService.makeRefinementMessage(locations);
                slackApi.sendResponse(commandData.getResponseUrl(), refinementMessage);
                return;
            }

            ConnectionRequest request = new ConnectionRequest(locations, time, isArrivalTime);
            SlackMessage connectionOverviewMessage = makeConnectionOverview(request);
            slackApi.sendResponse(commandData.getResponseUrl(), connectionOverviewMessage);
        }
    }
}
