package nu.peg.slack.pt.endpoint;

import nu.peg.slack.pt.api.transport.TransportApi;
import nu.peg.slack.pt.api.transport.model.Location;
import nu.peg.slack.pt.model.Response;
import nu.peg.slack.pt.service.OauthService;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("v1")
@Produces("application/json;charset=UTF-8")
public class SlackEndpoint {

    @GET
    @Path("oauth")
    public Response<String> oauth(@QueryParam("code") String code) {
        // TODO DI?
        OauthService os = new OauthService();
        String token = os.authorize(code);

        if (token == null) {
            return Response.error("Invalid code");
        } else {
            return Response.ok(String.format("Authorization successful: %s", token));
        }
    }

    @GET
    @Path("connections")
    public Response<List<Location>> connections() {
        // TODO call Service

        TransportApi api = new TransportApi();
        List<Location> locations = api.queryLocation("wander");

        return Response.ok(locations);
    }

    @POST
    @Path("interactive")
    public void interactive() {
        // TODO call Service

        return;
    }
}
