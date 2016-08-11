package nu.peg.slack.pt.api.transport.model;

public class Service {

    public String regular, irregular;

    public Service() {
    }

    public Service(String regular, String irregular) {
        this.regular = regular;
        this.irregular = irregular;
    }

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }

    public String getIrregular() {
        return irregular;
    }

    public void setIrregular(String irregular) {
        this.irregular = irregular;
    }
}
