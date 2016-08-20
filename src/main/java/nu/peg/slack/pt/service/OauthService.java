package nu.peg.slack.pt.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OauthService {

    private String clientId, clientSecret;

    public OauthService(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    /**
     * Calls the oauth.access resource at Slack
     *
     * @param oauthCode The code to send to slack
     * @return Authorization token from slack or null if authorization failed
     */
    public String authorize(String oauthCode) {

        Map<String, Object> bodyParams = new HashMap<>();
        bodyParams.put("client_id", clientId);
        bodyParams.put("client_secret", clientSecret);
        bodyParams.put("code", oauthCode);


        HttpResponse<JsonNode> jsonResponse = null;
        try {
            jsonResponse = Unirest.post("https://slack.com/api/oauth.access")
                                  .fields(bodyParams)
                                  .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        if (jsonResponse == null)
            return null;

        JSONObject responseObject = jsonResponse.getBody().getObject();
        return responseObject.has("access_token") ? responseObject.getString("access_token") : null;
    }

}
