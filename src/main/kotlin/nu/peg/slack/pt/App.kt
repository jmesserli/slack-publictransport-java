package nu.peg.slack.pt

import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder

import com.mashape.unirest.http.Unirest

import nu.peg.slack.pt.api.transport.model.Connection
import nu.peg.slack.pt.model.ConnectionRequest

import java.io.IOException
import java.time.format.DateTimeFormatter
import java.util.Properties
import java.util.concurrent.TimeUnit

import javax.servlet.ServletContextEvent
import javax.servlet.ServletContextListener

class App : ServletContextListener {

    override fun contextInitialized(servletContextEvent: ServletContextEvent) {
        config = Properties()

        try {
            config.load(javaClass.getResourceAsStream("/config.properties"))
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    override fun contextDestroyed(servletContextEvent: ServletContextEvent) {
        try {
            Unirest.shutdown()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    companion object {

        val SIMPLE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm")

        val ACTION_LOCATION_REFINE_NAME = "locationRefinement"
        val ACTION_OVERVIEW_SELECT_NAME = "overviewSelect"

        var config: Properties

        var connectionRequestCache = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.MINUTES).build<String, ConnectionRequest>()
        var connectionsCache = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.MINUTES).build<String, List<Connection>>()

        fun validateToken(token: String): Boolean {
            return config["slack.server.token"] == token
        }
    }
}
