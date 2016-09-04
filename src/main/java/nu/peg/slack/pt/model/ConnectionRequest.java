package nu.peg.slack.pt.model;

import java.time.LocalTime;

import lombok.*;

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
