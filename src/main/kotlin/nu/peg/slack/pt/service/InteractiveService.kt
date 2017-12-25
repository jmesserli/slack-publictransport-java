package nu.peg.slack.pt.service

import nu.peg.slack.pt.api.slack.model.InteractivePostData

interface InteractiveService {
    fun handleInteractive(interactiveData: InteractivePostData)
}
