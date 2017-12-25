package nu.peg.slack.pt.api.transport.model

import lombok.Data

@Data
class Connection {

    private val from: Checkpoint? = null
    private val to: Checkpoint? = null
    private val duration: String? = null
    private val service: Service? = null
    private val products: List<String>? = null
    private val capacity1st: Int? = null
    private val capacity2nd: Int? = null
    private val sections: List<Section>? = null

}
