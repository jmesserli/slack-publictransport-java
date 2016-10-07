# slack-publictransport-java :train2:

A Slack app that provides users in Switzerland with public transport information. The source for this information is the [Opendata Transport API](http://transport.opendata.ch/docs.html).

If you want to use this for your own Slack, you can either use the instance at [slack-publictransport-api2.jmnw.me](slack-publictransport-api2.jmnw.me) and add it by clicking the Slack button on that page. To host your own instance, proceed to the **Installation** section.

## Installation :rocket:

Slack does not allow the creation of app commands if there is no page listening for the command, so we will first deploy the application without much configuration and then register the Slack app, so that we can finish the configuration.

### Server setup :wrench:

To allow the application to run, you have to copy `src/main/resources/config.example.properties` to `src/main/resources/config.properties` since this file is required when the application starts up. 

You can then run a [Maven](https://maven.apache.org/) install by running `mvn install` in the project root folder. This will generate a `slack-publictransport.war` file in the `target` folder. Deploy this WAR somewhere internet-accessible on your preferred container (tested only on Tomcat 8). Note that you **must** provide the application over a secured connection (https) because Slack enforces this. You can use e.g. Nginx to terminate the secured connection and proxy to the unsecured application. **From now on, I will use `$BASE` whenever you need to enter the secure URL you deployed the WAR at for simplicity.**

### Slack setup :comment:

We can now register our application on Slack's [Your Apps](https://api.slack.com/apps) page by clicking `Create New App`. Enter a snarky app name and click on `Create App`.

The next step is to set up the OAuth URL. For this, click on `OAuth & Permissions` in the lefthand menu. In the generous `Redirect URL(s)` field there, enter `$BASE/api/v1/oauth`. 

After you have `Save Changes`d, we will move on to `Interactive Messages`. On that page, all you need to do is click `Enable Interactive Messages`, enter `$BASE/api/v1/interactive` and click `Enable Interactive Messages` once again.

We will now create the actual command on the `Slash Commands` page. The `Create New Command` button is your friend which opens up quite a form. The only thing that is a given is the `Request URL`, which needs to be set to `$BASE/api/v1/connections`. You can customize the rest to your likings. The syntax for the parameters is `"from" "to" (time[an|ab]) ` where the quotation marks are optional if the name of the station is only one word and time is in the format HH:mm.

### Configuring the server :fast:

For the final part, open the `Basic Information` page for your Slack app. You should be able to find `Client ID`,  `Client Secret` and `Verification Token` on this page. Those correspond to `slack.client.id`, `slack.client.secret` and `slack.server.token` in the configuration file `src/main/resources/config.properties` which you can now set to the correct values. 

After that you also have to update the `Client ID` in `src/main/webapp/index.jsp`:

```html
<!-- ... -->
<a href="https://slack.com/oauth/authorize?scope=commands&client_id=<Your client id here>">
<!-- ... -->
```

Run a Maven install and redeploy your app. By visiting `$BASE` and clicking on the Slack button, you should be able to add the application to your team's Slack. Congratulations! ​:smiley:​