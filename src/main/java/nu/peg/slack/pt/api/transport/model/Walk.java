package nu.peg.slack.pt.api.transport.model;

public class Walk {
    private String duration;

    public Walk() {
    }

    public Walk(String duration) {
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
