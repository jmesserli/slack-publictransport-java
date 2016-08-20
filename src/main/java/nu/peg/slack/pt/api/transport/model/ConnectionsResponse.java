package nu.peg.slack.pt.api.transport.model;

import java.util.List;

import lombok.Data;

@Data
public class ConnectionsResponse {

    private List<Connection> connections;

}
