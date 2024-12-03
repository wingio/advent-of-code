package puzzles

import print
import readInput

// Day 3

private val input = readInput(day = 3)

val multRx = "mul\\((\\d+),(\\d+)\\)".toRegex()

fun main() {
    // Part 1
    multRx.findAll(input)
        .sumOf { it.groupValues[1].toInt() * it.groupValues[2].toInt() }
        .print { "(Part 1) Sum: $it" }

    // Part 2
    parseInstructions(input)
        .sumOf { (x, y) -> x * y }
        .print { "(Part 2) Conditional sum: $it" }
}

fun parseInstructions(inp: String): List<Pair<Int, Int>> {
    val matches = multRx.findAll(inp)

    var cursor = 0
    val parts = mutableListOf<Pair<Int, Int>>()
    var shouldExecute = true

    matches.forEach {
        val chunk = inp.substring(cursor, it.range.first)
        val dontIdx = chunk.lastIndexOf("don't()")
        val doIdx = chunk.lastIndexOf("do()")

        shouldExecute = if(dontIdx == -1 && doIdx == -1) shouldExecute else doIdx > dontIdx

        if (shouldExecute) parts += it.groupValues[1].toInt() to it.groupValues[2].toInt()
        cursor = it.range.last + 1
    }

    return parts
}
