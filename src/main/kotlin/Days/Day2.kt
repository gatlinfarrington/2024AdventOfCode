package org.gatlin.Days

import org.gatlin.Helpers
import kotlin.math.abs

class Day2: Day() {
    override fun solve(){
        val input = Helpers.readFileFromResources("2.txt")
        val reports = input.split("\n").map { it.split(" ")}.map { report -> report.map { it.toInt() } }

        //part 1
        val safeReports = reports.filter { it.zipWithNext().all { (a,b) -> a <= b } || it.zipWithNext().all { (a,b) -> a >= b } }
            .filter { it.zipWithNext().all { (a,b) -> (abs(a-b) in 1..3) } }

        //part 2
        val unsafeReports = reports.filterNot { safeReports.contains(it) }
        val newSafeReports = unsafeReports.filter { report ->
            report.indices.any { index ->
                val sublist = report.toMutableList().apply { removeAt(index) }
                (sublist.zipWithNext().all { (a, b) -> a <= b } && // Ascending condition
                sublist.zipWithNext().all { (a, b) -> abs(a - b) in 1..3 })
                ||
                (sublist.zipWithNext().all { (a, b) -> a >= b } && // Descending condition
                sublist.zipWithNext().all { (a, b) -> abs(a - b) in 1..3 })
            }
        }

        print("safe report count: ${safeReports.size} || new safe reports count: ${safeReports.size + newSafeReports.size}")

    }
}