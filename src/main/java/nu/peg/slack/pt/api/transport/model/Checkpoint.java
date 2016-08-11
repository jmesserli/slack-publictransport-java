package nu.peg.slack.pt.api.transport.model;

public class Checkpoint {

    private Location station;
    private String arrival, departure, platform;
    private Prognosis prognosis;

    public Checkpoint() {
    }

    public Checkpoint(Location station, String arrival, String departure, String platform, Prognosis prognosis) {
        this.station = station;
        this.arrival = arrival;
        this.departure = departure;
        this.platform = platform;
        this.prognosis = prognosis;
    }

    public Location getStation() {
        return station;
    }

    public void setStation(Location station) {
        this.station = station;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Prognosis getPrognosis() {
        return prognosis;
    }

    public void setPrognosis(Prognosis prognosis) {
        this.prognosis = prognosis;
    }
}
