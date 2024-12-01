package puzzles

import getNumbersFromLine
import print
import readInput
import kotlin.math.absoluteValue

// Day 1

private val input = readInput(day = 1)

fun main() {
    val numbers = input.lines().map { getNumbersFromLine(it) }
    val list1 = numbers.map { it.first() }.sorted()
    val list2 = numbers.map { it.last() }.sorted()

    // Part 1
    list1
        .zip(list2)
        .sumOf { (it.first - it.second).absoluteValue }
        .print { "Solution to part 1: $it" }

    // Part 2
    list1
        .sumOf { item1 ->
            item1 * list2.count { item2 -> item2 == item1 }
        }
        .print { "Solution to part 2: $it" }
}

