package puzzles

import day

// Day 19

fun main() = day(19, example = false) {
    val cache = mutableMapOf<String, Long>()
    val (towels, patterns) = input.split("\n\n").run { first().split(", ") to last().lines() }

    part1 { patterns.count { towels.find(it, cache) >= 1 } }
    part2 { patterns.sumOf { towels.find(it, cache) } }
}

private fun List<String>.find(pattern: String, cache: MutableMap<String, Long>): Long {
    if (pattern.isEmpty()) return 1
    return cache[pattern] ?:
        sumOf { towel -> if (pattern.startsWith(towel)) find(pattern.drop(towel.length), cache) else 0 }
        .also { cache[pattern] = it }
}