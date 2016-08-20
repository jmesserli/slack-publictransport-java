package nu.peg.slack.pt.api.transport.model;

public class Location {

    private String id, name;
    private Integer score;
    private Coordinates coordinates;
    private Double distance;

    public Location() {
    }

    public Location(String id, String name, Integer score, Coordinates coordinates, Double distance) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.coordinates = coordinates;
        this.distance = distance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
