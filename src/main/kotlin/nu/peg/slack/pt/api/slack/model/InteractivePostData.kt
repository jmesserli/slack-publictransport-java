package nu.peg.slack.pt.api.slack.model

import com.google.gson.Gson

import javax.ws.rs.FormParam

import lombok.Data
import lombok.Getter

@Data
class InteractivePostData {

    @FormParam("payload")
    private val payload: String? = null

    @Getter(lazy = true)
    private val payloadObject = asObject

    private val asObject: InteractivePayload
        get() = Gson().fromJson(payload, InteractivePayload::class.java)
}
