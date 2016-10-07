package nu.peg.slack.pt.api.slack;

import com.google.gson.GsonBuilder;

import nu.peg.slack.pt.api.slack.model.SlackMessage;

public class DevelopmentLoggingSlackApi extends SlackApi {

    public DevelopmentLoggingSlackApi() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public boolean sendResponse(String responseUrl, SlackMessage message) {
        System.err.printf("Sending response to \"%s\":%n%s%n", responseUrl, gson.toJson(message));

        return true;
    }
}
