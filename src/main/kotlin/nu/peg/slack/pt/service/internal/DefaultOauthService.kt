package nu.peg.slack.pt.service.internal

import com.mashape.unirest.http.*
import com.mashape.unirest.http.exceptions.UnirestException

import nu.peg.slack.pt.service.OauthService

import org.json.JSONObject

import java.util.HashMap

class DefaultOauthService(private val clientId: String, private val clientSecret: String) : OauthService {

    /**
     * Calls the oauth.access resource at Slack
     *
     * @param oauthCode The code to send to slack
     * @return Authorization token from slack or null if authorization failed
     */
    override fun authorize(oauthCode: String): String? {

        val bodyParams = HashMap<String, Any>()
        bodyParams.put("client_id", clientId)
        bodyParams.put("client_secret", clientSecret)
        bodyParams.put("code", oauthCode)


        var jsonResponse: HttpResponse<JsonNode>? = null
        try {
            jsonResponse = Unirest.post("https://slack.com/api/oauth.access")
                    .fields(bodyParams)
                    .asJson()
        } catch (e: UnirestException) {
            e.printStackTrace()
        }

        if (jsonResponse == null)
            return null

        val responseObject = jsonResponse!!.getBody().getObject()
        return if (responseObject.has("access_token")) responseObject.getString("access_token") else null
    }

}
