package puzzles

import getNumbersFromLine
import print
import readInput
import kotlin.math.abs
import kotlin.math.absoluteValue
import kotlin.math.sign

// Day 2

private val input = readInput(day = 2)

fun main() {
    val reports = input.lines().map { getNumbersFromLine(it) }

    reports
        .count { reportIsSafe(it) }
        .print { "(Part 1) Safe reports: $it" }

    reports
        .count { checkWithDampener(it) }
        .print { "(Part 2) Safe reports with dampener: $it" }
}

fun reportIsSafe(report: List<Int>, dampen: Boolean = false): Boolean {
    val deltas = report
        .windowed(2, 1)
        .map { it.first() - it.last() }

    return deltas.all { it.absoluteValue in 1..3 } && deltas.map { it.sign }.toSet().size == 1
}

fun checkWithDampener(report: List<Int>): Boolean {
    if (reportIsSafe(report)) return true

    return report.indices.any {
        reportIsSafe(report.toMutableList().apply { removeAt(it) })
    }
}