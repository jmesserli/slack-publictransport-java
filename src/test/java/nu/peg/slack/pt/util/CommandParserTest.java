package nu.peg.slack.pt.util;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class CommandParserTest {

    @Test
    public void testGetArguments() throws Exception {
        String input = "\"bern bahnhof\" sulgenau     \" bern\"";

        assertThat(CommandParser.getArguments(input))
                .containsExactly("bern bahnhof", "sulgenau", "bern")
                .inOrder();
    }
}