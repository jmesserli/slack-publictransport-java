package nu.peg.slack.pt.api.transport.model;

import java.util.List;

public class Connection {

    private Checkpoint from, to;
    private String duration;
    private Service service;
    private List<String> products;
    private Integer capacity1st, capacity2nd;
    private List<Section> sections;

    public Connection() {
    }

    public Connection(Checkpoint from, Checkpoint to, String duration, Service service, List<String> products, Integer capacity1st, Integer capacity2nd, List<Section> sections) {
        this.from = from;
        this.to = to;
        this.duration = duration;
        this.service = service;
        this.products = products;
        this.capacity1st = capacity1st;
        this.capacity2nd = capacity2nd;
        this.sections = sections;
    }

    public Checkpoint getFrom() {
        return from;
    }

    public void setFrom(Checkpoint from) {
        this.from = from;
    }

    public Checkpoint getTo() {
        return to;
    }

    public void setTo(Checkpoint to) {
        this.to = to;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
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

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
