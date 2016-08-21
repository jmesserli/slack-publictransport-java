package nu.peg.slack.pt.service;

import nu.peg.slack.pt.api.slack.model.CommandPostData;
import nu.peg.slack.pt.api.slack.model.SlackMessage;
import nu.peg.slack.pt.api.transport.model.Connection;
import nu.peg.slack.pt.model.ConnectionRequest;

public interface ConnectionService {

    public void handleCommand(CommandPostData commandData);

    public SlackMessage makeConnectionOverview(ConnectionRequest request);

    public SlackMessage makeConnectionDetail(Connection connection);
}
