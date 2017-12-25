package nu.peg.slack.pt

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class SlackPublicTransportApplication

fun main(args: Array<String>) {
    SpringApplication.run(SlackPublicTransportApplication::class.java, *args)
}
