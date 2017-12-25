package nu.peg.slack.pt.api.slack

import com.google.gson.GsonBuilder

import nu.peg.slack.pt.api.slack.model.SlackMessage

class DevelopmentLoggingSlackApi : SlackApi() {
    init {
        gson = GsonBuilder().setPrettyPrinting().create()
    }

    override fun sendResponse(responseUrl: String, message: SlackMessage): Boolean {
        System.err.printf("Sending response to \"%s\":%n%s%n", responseUrl, gson.toJson(message))

        return true
    }
}
