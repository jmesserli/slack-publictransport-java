package nu.peg.slack.pt.endpoint;

import com.google.gson.Gson;
import nu.peg.slack.pt.App;
import nu.peg.slack.pt.api.slack.SlackApi;
import nu.peg.slack.pt.api.slack.model.CommandPostData;
import nu.peg.slack.pt.api.slack.model.InteractivePostData;
import nu.peg.slack.pt.model.Response;
import nu.peg.slack.pt.service.ConnectionService;
import nu.peg.slack.pt.service.InteractiveService;
import nu.peg.slack.pt.service.OauthService;

import javax.inject.Inject;
import javax.ws.rs.*;

import static javax.ws.rs.core.Response.Status;
import static javax.ws.rs.core.Response.status;

@Path("v1")
@Produces("application/json")
public class SlackEndpoint {

    private OauthService oauthService;
    private ConnectionService connectionService;
    private InteractiveService interactiveService;
    private SlackApi slackApi;

    private Gson gson;

    @Inject
    public SlackEndpoint(OauthService oauthService, ConnectionService connectionService, InteractiveService interactiveService, SlackApi slackApi) {
        this.oauthService = oauthService;
        this.connectionService = connectionService;
        this.interactiveService = interactiveService;
        this.slackApi = slackApi;

        this.gson = new Gson();
    }

    @GET
    @Path("oauth")
    public Response<String> oauth(@QueryParam("code") String code) {
        String token = oauthService.authorize(code);

        if (token == null) {
            return Response.error("Invalid code");
        } else {
            return Response.ok(String.format("Authorization successful: %s", token));
        }
    }

    @POST
    @Path("connections")
    @Consumes("application/x-www-form-urlencoded")
    public javax.ws.rs.core.Response connections(@BeanParam CommandPostData commandData) {
        boolean validToken = App.validateToken(commandData.getToken());
        if (!validToken) {
            return status(Status.UNAUTHORIZED).build();
        }

        connectionService.handleCommand(commandData);

        return status(200).build();
    }

    @POST
    @Path("interactive")
    public javax.ws.rs.core.Response interactive(@BeanParam InteractivePostData interactiveData) {
        String token = interactiveData.getPayloadObject().getToken();
        boolean validToken = App.validateToken(token);

        if (!validToken) {
            return status(Status.UNAUTHORIZED).build();
        }

        interactiveService.handleInteractive(interactiveData);
        return status(Status.OK).build();
    }
}
