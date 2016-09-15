package nu.peg.slack.pt.util;

import com.google.common.base.CharMatcher;
import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class CommandParser {

    private static final Pattern pattern = Pattern.compile("(\"([\\wäöüÄÖÜ,: ]*)\"|([\\wäöüÄÖÜ,:]*))");
    private static final CharMatcher trimMatcher = CharMatcher.is('"').or(CharMatcher.WHITESPACE);

    public static List<String> getArguments(String text) {
        List<String> arguments = new ArrayList<>();

        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) arguments.add(matcher.group());

        return arguments.stream()
                        .map(trimMatcher::trimFrom)
                        .filter(str -> !Strings.isNullOrEmpty(str))
                        .collect(Collectors.toList());
    }
}
