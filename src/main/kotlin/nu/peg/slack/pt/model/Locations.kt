package nu.peg.slack.pt.model

import nu.peg.slack.pt.api.transport.model.Location

import lombok.Data
import lombok.NoArgsConstructor

@Data
@NoArgsConstructor
class Locations(private val originalFrom: String, private val originalTo: String, private val fromOptions: List<Location>, private val toOptions: List<Location>) {
    private var from: Location? = null
    private var to: Location? = null

    val isUnique: Boolean
        get() = from != null && to != null

    init {

        if (fromOptions.size == 1) {
            from = fromOptions[0]
        }

        if (toOptions.size == 1) {
            to = toOptions[0]
        }
    }

    fun choose(fromOrTo: String, chosenLocationIdx: Int) {
        if (fromOrTo == "from") {
            from = fromOptions[chosenLocationIdx]
        } else if (fromOrTo == "to") {
            to = toOptions[chosenLocationIdx]
        }
    }
}
