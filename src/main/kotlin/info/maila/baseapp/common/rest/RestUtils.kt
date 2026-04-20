package info.maila.baseapp.common.rest

import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit.MILLIS
import kotlin.time.Duration
import kotlin.time.DurationUnit.HOURS
import kotlin.time.toDuration

fun expires(add: Duration = 1.toDuration(HOURS)): String {
    val now =
        OffsetDateTime.now(ZoneOffset.UTC)
            .plus(add.inWholeMilliseconds, MILLIS)
            .format(DateTimeFormatter.RFC_1123_DATE_TIME)
    return now
}