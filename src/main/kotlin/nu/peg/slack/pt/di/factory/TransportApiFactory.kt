package nu.peg.slack.pt.di.factory

import nu.peg.slack.pt.api.transport.TransportApi

import org.glassfish.hk2.api.Factory

class TransportApiFactory : Factory<TransportApi> {
    fun provide(): TransportApi {
        return TransportApi()
    }

    fun dispose(instance: TransportApi) {

    }
}
