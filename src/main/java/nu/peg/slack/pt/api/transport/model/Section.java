package nu.peg.slack.pt.api.transport.model;

public class Section {
    private Journey journey;
    private String walk;
    private Checkpoint departure, arrival;

    public Section() {
    }

    public Section(Journey journey, String walk, Checkpoint departure, Checkpoint arrival) {
        this.journey = journey;
        this.walk = walk;
        this.departure = departure;
        this.arrival = arrival;
    }

    public Journey getJourney() {
        return journey;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }

    public String getWalk() {
        return walk;
    }

    public void setWalk(String walk) {
        this.walk = walk;
    }

    public Checkpoint getDeparture() {
        return departure;
    }

    public void setDeparture(Checkpoint departure) {
        this.departure = departure;
    }

    public Checkpoint getArrival() {
        return arrival;
    }

    public void setArrival(Checkpoint arrival) {
        this.arrival = arrival;
    }
}
