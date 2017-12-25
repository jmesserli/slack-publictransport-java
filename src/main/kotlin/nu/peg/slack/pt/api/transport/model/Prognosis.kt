package nu.peg.slack.pt.api.transport.model

import lombok.Data

@Data
class Prognosis {

    private val platform: String? = null
    private val departure: String? = null
    private val arrival: String? = null
    private val capacity1st: Int? = null
    private val capacity2nd: Int? = null

}
