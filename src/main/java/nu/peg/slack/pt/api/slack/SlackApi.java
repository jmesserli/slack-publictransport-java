package nu.peg.slack.pt.api.slack;

import com.google.gson.Gson;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import nu.peg.slack.pt.api.slack.model.SlackMessage;

import org.jvnet.hk2.annotations.Service;

@Service
public class SlackApi {

    private Gson gson;

    public SlackApi() {
        gson = new Gson();
    }

    public boolean sendResponse(String responseUrl, SlackMessage message) {
        HttpResponse<String> stringResponse = null;

        try {
            stringResponse = Unirest.post(responseUrl)
                                    .body(gson.toJson(message))
                                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return stringResponse.getStatus() == 200;
    }

}
