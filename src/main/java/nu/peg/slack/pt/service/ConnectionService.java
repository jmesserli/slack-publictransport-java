package nu.peg.slack.pt.service;

import nu.peg.slack.pt.api.transport.TransportApi;

import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;

@Service
public class ConnectionService {

    private TransportApi transportApi;

    @Inject
    public ConnectionService(TransportApi transportApi) {
        this.transportApi = transportApi;
    }
}
