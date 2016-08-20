package nu.peg.slack.pt.api.slack.model;


import lombok.Data;

@Data
public class Action {

    private String name;
    private String text;
    private ActionButtonStyle style = ActionButtonStyle.Default;
    private final String type = "button";
    private String value;

}
