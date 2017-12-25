package nu.peg.slack.pt.model

import nu.peg.slack.pt.model.Status.Error
import nu.peg.slack.pt.model.Status.*

class Response<T> {

    var status: Status
    var data: T
    var error: String

    constructor() {}

    constructor(data: T) {
        this.status = Ok
        this.data = data
    }

    constructor(error: String) {
        this.status = Error
        this.error = error
    }

    constructor(status: Status, data: T, error: String) {
        this.status = status
        this.data = data
        this.error = error
    }

    companion object {

        fun <E> error(error: String): Response<E> {
            return Response<E>(Error, null, error)
        }

        fun <E> ok(data: E): Response<E> {
            return Response(Ok, data, null)
        }
    }
}
