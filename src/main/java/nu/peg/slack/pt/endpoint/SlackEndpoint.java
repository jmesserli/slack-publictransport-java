package nu.peg.slack.pt.endpoint;

import nu.peg.slack.pt.model.Response;
import nu.peg.slack.pt.service.OauthService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("v1")
@Produces("application/json")
public class SlackEndpoint {

    @Inject
    private OauthService oauthService;

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

    @GET
    @Path("connections")
    public Response<Void> connections() {
        // TODO call Service

        return Response.ok(null);
    }

    @POST
    @Path("interactive")
    public void interactive() {
        // TODO call Service

        return;
    }
}
