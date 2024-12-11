package org.gatlin

import java.time.LocalDate
import java.time.ZoneId


fun main() {
    val estZoneId = ZoneId.of("America/New_York")
    val currentDayOfMonthInEST = LocalDate.now(estZoneId).dayOfMonth
    println("Today's day of the month is: $currentDayOfMonthInEST")

    val solution = Helpers.getSolveMethodForDay(7)
    solution()
}