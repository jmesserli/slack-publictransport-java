package nu.peg.slack.pt.api.slack.model;

import com.google.gson.Gson;
import lombok.Data;
import lombok.Getter;

import javax.ws.rs.FormParam;

@Data
public class InteractivePostData {

    @FormParam("payload")
    private String payload;

    @Getter(lazy = true)
    private final InteractivePayload payloadObject = getAsObject();

    private InteractivePayload getAsObject() {
        return new Gson().fromJson(payload, InteractivePayload.class);
    }
}
