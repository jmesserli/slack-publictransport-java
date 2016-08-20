package nu.peg.slack.pt.api.transport.model;

import java.util.List;

public class ConnectionsResponse {

    private List<Connection> connections;

    public ConnectionsResponse() {
    }

    public ConnectionsResponse(List<Connection> connections) {
        this.connections = connections;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }
}
