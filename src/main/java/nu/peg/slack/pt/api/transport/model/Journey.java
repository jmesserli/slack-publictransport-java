package nu.peg.slack.pt.api.transport.model;

import java.util.List;

public class Journey {

    private String name, category, number, operator, to;
    private Integer categoryCode;
    private List<Checkpoint> passList;
    private Integer capacity1st, capacity2nd;

    public Journey() {
    }

    public Journey(String name, String category, String number, String operator, String to, Integer categoryCode, List<Checkpoint> passList, Integer capacity1st, Integer capacity2nd) {
        this.name = name;
        this.category = category;
        this.number = number;
        this.operator = operator;
        this.to = to;
        this.categoryCode = categoryCode;
        this.passList = passList;
        this.capacity1st = capacity1st;
        this.capacity2nd = capacity2nd;
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

    public Integer getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(Integer categoryCode) {
        this.categoryCode = categoryCode;
    }

    public List<Checkpoint> getPassList() {
        return passList;
    }

    public void setPassList(List<Checkpoint> passList) {
        this.passList = passList;
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
