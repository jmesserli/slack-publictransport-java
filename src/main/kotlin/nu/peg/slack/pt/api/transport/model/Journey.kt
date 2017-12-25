package nu.peg.slack.pt.api.transport.model

import lombok.Data

@Data
class Journey {

    private val name: String? = null
    private val category: String? = null
    private val number: String? = null
    private val operator: String? = null
    private val to: String? = null
    private val categoryCode: Int? = null
    private val passList: List<Checkpoint>? = null
    private val capacity1st: Int? = null
    private val capacity2nd: Int? = null

}
