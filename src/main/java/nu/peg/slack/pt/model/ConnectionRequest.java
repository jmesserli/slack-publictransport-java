package nu.peg.slack.pt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConnectionRequest {

    private Locations locations;
    private LocalTime time;
    private boolean isArrivalTime;

    public ConnectionRequest(Locations locations) {
        this.locations = locations;
        time = LocalTime.now();
        isArrivalTime = false;
    }

}
