package puzzles

import day
import getNumbersFromLine
import kotlin.math.absoluteValue
import kotlin.math.sign

// Day 2

fun main() = day(2) {
    val reports = input.lines().map { getNumbersFromLine(it) }

    part1 {
        reports.count { reportIsSafe(it) }
    }

    part2 {
        reports.count { checkWithDampener(it) }
    }
}

fun reportIsSafe(report: List<Int>): Boolean {
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