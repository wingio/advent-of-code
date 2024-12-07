package puzzles

import day

// Day 3

val multRx = "mul\\((\\d+),(\\d+)\\)".toRegex()

fun main() = day(3) {
    part1 {
        multRx.findAll(input)
            .sumOf { it.groupValues[1].toInt() * it.groupValues[2].toInt() }
    }

    part2 {
        parseInstructions(input)
            .sumOf { (x, y) -> x * y }
    }
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
