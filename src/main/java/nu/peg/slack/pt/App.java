package nu.peg.slack.pt;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.mashape.unirest.http.Unirest;
import nu.peg.slack.pt.api.transport.model.Connection;
import nu.peg.slack.pt.model.ConnectionRequest;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class App implements ServletContextListener {

    public static final String ACTION_LOCATION_REFINE_NAME = "locationRefinement";
    public static final String ACTION_OVERVIEW_SELECT_NAME = "overviewSelect";

    public static Properties config;

    public static Cache<String, ConnectionRequest> connectionRequestCache = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.MINUTES).build();
    public static Cache<String, List<Connection>> connectionsCache = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.MINUTES).build();

    public static boolean validateToken(String token) {
        return config.get("slack.server.token").equals(token);
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        config = new Properties();

        try {
            config.load(getClass().getResourceAsStream("/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            Unirest.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
