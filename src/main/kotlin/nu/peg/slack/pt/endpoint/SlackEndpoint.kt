package nu.peg.slack.pt.endpoint

import nu.peg.slack.pt.App
import nu.peg.slack.pt.api.slack.model.CommandPostData
import nu.peg.slack.pt.api.slack.model.InteractivePostData
import nu.peg.slack.pt.model.Response
import nu.peg.slack.pt.service.*

import javax.inject.Inject
import javax.ws.rs.*

import javax.ws.rs.core.Response.*

@Path("v1")
@Produces("application/json")
class SlackEndpoint @Inject
constructor(private val oauthService: OauthService, private val connectionService: ConnectionService, private val interactiveService: InteractiveService) {

    @GET
    @Path("oauth")
    fun oauth(@QueryParam("code") code: String): Response<String> {
        val token = oauthService.authorize(code)

        return if (token == null) {
            Response.error("Invalid code")
        } else {
            Response.ok(String.format("Authorization successful: %s", token))
        }
    }

    @POST
    @Path("connections")
    @Consumes("application/x-www-form-urlencoded")
    fun connections(@BeanParam commandData: CommandPostData): javax.ws.rs.core.Response {
        val validToken = App.validateToken(commandData.getToken())
        if (!validToken) {
            return status(Status.UNAUTHORIZED).build()
        }

        connectionService.handleCommand(commandData)

        return status(200).build()
    }

    @POST
    @Path("interactive")
    fun interactive(@BeanParam interactiveData: InteractivePostData): javax.ws.rs.core.Response {
        val token = interactiveData.getPayloadObject().getToken()
        val validToken = App.validateToken(token)

        if (!validToken) {
            return status(Status.UNAUTHORIZED).build()
        }

        interactiveService.handleInteractive(interactiveData)
        return status(Status.OK).build()
    }
}
