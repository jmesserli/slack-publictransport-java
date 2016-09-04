package nu.peg.slack.pt.api.slack.model;

import nu.peg.slack.pt.api.transport.model.Connection;
import nu.peg.slack.pt.model.ConnectionRequest;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import static nu.peg.slack.pt.App.ACTION_OVERVIEW_SELECT_NAME;

@Data
@EqualsAndHashCode(callSuper = true)
public class ConnectionOverviewMessage extends SlackMessage {

    private final String color = "#fff";

    public ConnectionOverviewMessage(ConnectionRequest connectionRequest, List<Connection> connections) {
        setText(String.format("Ihre Verbindungen von *%s nach %s*:", connectionRequest.getLocations().getFrom().getName(), connectionRequest.getLocations().getTo().getName()));
        setAttachments(new ArrayList<>());
        connections.stream().map(OverviewAttachment::new).forEach(getAttachments()::add);

        List<Action> actions = new ArrayList<>();
        for (int i = 0; i < connections.size(); i++) {
            Action action = new Action();
            action.setText(String.format("Verbindung %d", i + 1));
            action.setStyle(i == 0 ? ActionButtonStyle.Primary : ActionButtonStyle.Default);
            action.setName(ACTION_OVERVIEW_SELECT_NAME);
            action.setValue(String.valueOf(i));

            actions.add(action);
        }

        ActionAttachment actionAttachment = new ActionAttachment();
        actionAttachment.setText("Bitte wähle die gewünschte Verbingung:");
        actionAttachment.setColor("#fff");
        actionAttachment.setActions(actions);

        getAttachments().add(actionAttachment);
    }
}
