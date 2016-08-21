package nu.peg.slack.pt.service;

import nu.peg.slack.pt.api.slack.model.InteractivePostData;

public interface InteractiveService {
    public void handleInteractive(InteractivePostData interactiveData);
}
