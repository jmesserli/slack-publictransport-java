package nu.peg.slack.pt.model;

import static nu.peg.slack.pt.model.Status.Error;
import static nu.peg.slack.pt.model.Status.Ok;

public class Response<T> {

    public Status status;
    public T data;
    public String error;

    public Response() {
    }

    public Response(T data) {
        this.status = Ok;
        this.data = data;
    }

    public Response(String error) {
        this.status = Error;
        this.error = error;
    }

    public Response(Status status, T data, String error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static <E> Response<E> error(String error) {
        return new Response<>(Error, null, error);
    }

    public static <E> Response<E> ok(E data) {
        return new Response<>(Ok, data, null);
    }
}
