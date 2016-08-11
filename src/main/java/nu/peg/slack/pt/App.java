package nu.peg.slack.pt;

import com.mashape.unirest.http.Unirest;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class App implements ServletContextListener {

    public static Properties config;

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
