package nu.peg.slack.pt.endpoint;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("v1")
public class SlackEndpoint {

    @GET
    @Path("oauth")
    public Response oauth(@QueryParam("code") String code) {
        // TODO call OauthService

        return Response.noContent().build();
    }

    @POST
    @Path("connections")
    public Response connections() {
        // TODO call Service

        return Response.noContent().build();
    }

    @POST
    @Path("interactive")
    public Response interactive() {
        // TODO call Service

        return Response.noContent().build();
    }
}
