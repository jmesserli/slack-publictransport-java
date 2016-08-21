package nu.peg.slack.pt.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConnectionRequest {

    private Locations locations;
    private LocalDateTime time;
    private boolean isArrivalTime;

    public ConnectionRequest(Locations locations) {
        this.locations = locations;
        time = LocalDateTime.now();
        isArrivalTime = false;
    }

}
