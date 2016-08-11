package nu.peg.slack.pt.api.transport.model;

public class Stop {

    private Location station;
    private String name, category, number, operator, to;

    public Stop() {
    }

    public Stop(Location station, String name, String category, String number, String operator, String to) {
        this.station = station;
        this.name = name;
        this.category = category;
        this.number = number;
        this.operator = operator;
        this.to = to;
    }

    public Location getStation() {
        return station;
    }

    public void setStation(Location station) {
        this.station = station;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
