package nu.peg.slack.pt.api.transport.model;

public class Prognosis {

    private String platform, departure, arrival;
    private Integer capacity1st, capacity2nd;

    public Prognosis() {
    }

    public Prognosis(String platform, String departure, String arrival, Integer capacity1st, Integer capacity2nd) {
        this.platform = platform;
        this.departure = departure;
        this.arrival = arrival;
        this.capacity1st = capacity1st;
        this.capacity2nd = capacity2nd;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public Integer getCapacity1st() {
        return capacity1st;
    }

    public void setCapacity1st(Integer capacity1st) {
        this.capacity1st = capacity1st;
    }

    public Integer getCapacity2nd() {
        return capacity2nd;
    }

    public void setCapacity2nd(Integer capacity2nd) {
        this.capacity2nd = capacity2nd;
    }
}
