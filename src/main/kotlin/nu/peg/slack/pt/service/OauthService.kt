package nu.peg.slack.pt.service

interface OauthService {
    fun authorize(code: String): String
}
