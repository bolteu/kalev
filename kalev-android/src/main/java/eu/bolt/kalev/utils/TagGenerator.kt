package eu.bolt.kalev.utils

import android.os.Build
import java.util.regex.Pattern

private val anonymousClassPattern = Pattern.compile("(\\$\\d+)+$")
private const val maxTagLength = 23

internal fun generateTag(stackDepth: Int): String {
    val stackTrace = Throwable().stackTrace
    check(stackTrace.size > stackDepth) { "Synthetic stacktrace didn't have enough elements: are you using proguard?" }
    val element = stackTrace[stackDepth]

    var tag = element.className
    val matcher = anonymousClassPattern.matcher(tag)
    if (matcher.find()) {
        tag = matcher.replaceAll("")
    }
    tag = tag.substring(tag.lastIndexOf('.') + 1)
    // Tag length limit was removed in API 24.
    return if (tag.length <= maxTagLength || Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        tag
    } else tag.substring(0, maxTagLength)
}
