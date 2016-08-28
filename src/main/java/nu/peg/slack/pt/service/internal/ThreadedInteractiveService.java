package nu.peg.slack.pt.service.internal;

import nu.peg.slack.pt.api.slack.SlackApi;
import nu.peg.slack.pt.api.slack.model.InteractivePayload;
import nu.peg.slack.pt.api.slack.model.InteractivePostData;
import nu.peg.slack.pt.api.slack.model.SlackMessage;
import nu.peg.slack.pt.api.transport.model.Connection;
import nu.peg.slack.pt.model.ConnectionRequest;
import nu.peg.slack.pt.service.ConnectionService;
import nu.peg.slack.pt.service.InteractiveService;
import nu.peg.slack.pt.service.LocationService;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static nu.peg.slack.pt.App.*;

public class ThreadedInteractiveService implements InteractiveService {

    private ConnectionService connectionService;
    private LocationService locationService;
    private SlackApi slackApi;

    @Inject
    public ThreadedInteractiveService(ConnectionService connectionService, LocationService locationService, SlackApi slackApi) {
        this.connectionService = connectionService;
        this.locationService = locationService;
        this.slackApi = slackApi;
    }

    @Override
    public void handleInteractive(InteractivePostData interactiveData) {
        new Thread(new InteractiveServiceThread(interactiveData)).start();
    }

    private class InteractiveServiceThread implements Runnable {

        private InteractivePostData interactiveData;

        InteractiveServiceThread(InteractivePostData interactiveData) {
            this.interactiveData = interactiveData;
        }

        private void handleLocationRefinement(String callbackId, String actionValue) {
            ConnectionRequest request = null;
            try {
                request = connectionRequestCache.get(callbackId, () -> null);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }

            // TODO: 28.08.2016 Handle null values from cache with error message

            String[] split = actionValue.split(":");
            String fromTo = split[0];
            String idx = split[1];

            request.getLocations().choose(fromTo, Integer.parseInt(idx));

            if (!request.getLocations().isUnique()) {
                SlackMessage refinementMessage = locationService.makeRefinementMessage(request);
                slackApi.sendResponse(interactiveData.getPayloadObject().getResponseUrl(), refinementMessage);
                return;
            }

            SlackMessage overviewMessage = connectionService.makeConnectionOverview(request);
            slackApi.sendResponse(interactiveData.getPayloadObject().getResponseUrl(), overviewMessage);
        }

        private void handleOverviewSelection(String callbackId, String actionValue) {
            List<Connection> connections = null;
            try {
                connections = connectionsCache.get(callbackId, () -> null);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }

            // TODO: 28.08.2016 Handle null values from cache with error message

            int connectionIdx = Integer.parseInt(actionValue);
            Connection connection = connections.get(connectionIdx);

            SlackMessage connectionDetailMessage = connectionService.makeConnectionDetail(connection);
            slackApi.sendResponse(interactiveData.getPayloadObject().getResponseUrl(), connectionDetailMessage);
        }

        @Override
        public void run() {
            InteractivePayload payload = interactiveData.getPayloadObject();
            String callbackId = payload.getCallbackId();
            InteractivePayload.Action action = payload.getActions().stream().findFirst().orElse(null);
            String name = action.getName();
            String actionValue = action.getValue();

            if (name.equals(ACTION_LOCATION_REFINE_NAME)) {
                handleLocationRefinement(callbackId, actionValue);
            } else if (name.equals(ACTION_OVERVIEW_SELECT_NAME)) {
                handleOverviewSelection(callbackId, actionValue);
            }
        }
    }
}
