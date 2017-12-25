package nu.peg.slack.pt.model

import java.time.LocalTime

import lombok.*

@Data
@NoArgsConstructor
@AllArgsConstructor
class ConnectionRequest(private val locations: Locations) {
    private val time: LocalTime
    private val isArrivalTime: Boolean

    init {
        time = LocalTime.now()
        isArrivalTime = false
    }

}
