package puzzles

import day

// Day 7

typealias Equation = Pair<Long, List<Long>>
val operators = mutableListOf<(Long, Long) -> Long>(
    { a, b -> a + b },
    { a, b -> a * b }
)

val concat: (Long, Long) -> Long = { a, b -> "$a$b".toLong() }

fun main() = day(7) {
    val equations: List<Equation> = input.lines().map {
        it.split(": ").run { first().toLong() to last().split(" ").map { n -> n.toLong() } }
    }

    part1 {
        equations.filter { it.isValid() }.sumOf { it.first }
    }

    part2 {
        operators.add(concat)
        equations.filter { it.isValid() }.sumOf { it.first }
    }
}

fun Equation.isValid(): Boolean {
    val (testValue, nums) = this
    if (nums.sum() == testValue) return true

    val prevValues = mutableListOf(nums.first())
    val calculated = mutableListOf<Long>()

    // Runs every possible set of operations
    nums.subList(1, nums.size).forEach { r ->
        prevValues.forEach { l ->
            operators.forEach { op ->
                val value = op(l, r)
                if (value <= testValue) calculated.add(value)
            }
        }

        // Housekeeping
        prevValues.clear()
        prevValues.addAll(calculated)
        calculated.clear()

        if (prevValues.contains(testValue)) return true // Return early instead of wasting time doing redundant operations
    }

    return false
}