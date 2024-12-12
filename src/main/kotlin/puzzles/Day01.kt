package puzzles

import day
import util.list.getIntsFromLine
import kotlin.math.absoluteValue

// Day 1

fun main() = day(1) {
    val numbers = input.lines().map { getIntsFromLine(it) }
    val list1 = numbers.map { it.first() }.sorted()
    val list2 = numbers.map { it.last() }.sorted()

    part1 {
        list1
            .zip(list2)
            .sumOf { (it.first - it.second).absoluteValue }
    }

    part2 {
        list1
            .sumOf { item1 ->
                item1 * list2.count { item2 -> item2 == item1 }
            }
    }
}

