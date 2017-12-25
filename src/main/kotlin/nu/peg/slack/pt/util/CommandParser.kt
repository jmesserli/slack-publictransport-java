package nu.peg.slack.pt.util

import com.google.common.base.CharMatcher
import com.google.common.base.Strings

import java.util.ArrayList
import java.util.regex.Matcher
import java.util.regex.Pattern
import java.util.stream.Collectors

object CommandParser {

    private val pattern = Pattern.compile("(\"([\\wäöüÄÖÜ,: ]*)\"|([\\wäöüÄÖÜ,:]*))")
    private val trimMatcher = CharMatcher.`is`('"').or(CharMatcher.WHITESPACE)

    fun getArguments(text: String): List<String> {
        val arguments = ArrayList<String>()

        val matcher = pattern.matcher(text)
        while (matcher.find()) arguments.add(matcher.group())

        return arguments.stream()
                .map<String>(Function<String, String> { trimMatcher.trimFrom(it) })
                .filter { str -> !Strings.isNullOrEmpty(str) }
                .collect<List<String>, Any>(Collectors.toList())
    }
}
