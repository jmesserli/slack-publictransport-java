package nu.peg.slack.pt.api.transport.model;

import lombok.Data;

import java.util.List;

@Data
public class ConnectionsResponse {

    private List<Connection> connections;

}
