package nu.peg.slack.pt.endpoint;

import nu.peg.slack.pt.App;
import nu.peg.slack.pt.api.slack.model.CommandPostData;
import nu.peg.slack.pt.model.Response;
import nu.peg.slack.pt.service.ConnectionService;
import nu.peg.slack.pt.service.OauthService;

import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("v1")
@Produces("application/json")
public class SlackEndpoint {

    private OauthService oauthService;
    private ConnectionService connectionService;

    @Inject
    public SlackEndpoint(OauthService oauthService, ConnectionService connectionService) {
        this.oauthService = oauthService;
        this.connectionService = connectionService;
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
    public void connections(@BeanParam CommandPostData commandData) {
        boolean validToken = App.validateToken(commandData.getToken());
        if (!validToken) {
            return;
        }

//        connectionService
    }

    @POST
    @Path("interactive")
    public void interactive() {
        // TODO call Service

        return;
    }
}
