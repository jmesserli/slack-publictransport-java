package nu.peg.slack.pt.api.slack.model;

import nu.peg.slack.pt.api.transport.model.Location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static nu.peg.slack.pt.App.ACTION_LOCATION_REFINE_NAME;

public class RefinementMessage extends SlackMessage {

    public RefinementMessage(String uncertainLocation, String fromTo, List<Location> locations, String callbackId) {
        setText(String.format("Für _%s_ gibt es mehrere Stationen", uncertainLocation));

        List<Action> actions = new ArrayList<>();
        for (int i = 0; i < locations.size(); i++) {
            Action action = new Action();
            action.setText(locations.get(i).getName());
            action.setStyle(i == 0 ? ActionButtonStyle.Primary : ActionButtonStyle.Default);
            action.setName(ACTION_LOCATION_REFINE_NAME);
            action.setValue(fromTo + ":" + i);

            actions.add(action);
        }

        ActionAttachment attachment = new ActionAttachment();
        attachment.setText("Bitte wähle die gewünschte Station");
        attachment.setColor("#000000");
        attachment.setActions(actions);
        attachment.setCallbackId(callbackId);

        setAttachments(Collections.singletonList(attachment));
    }
}
