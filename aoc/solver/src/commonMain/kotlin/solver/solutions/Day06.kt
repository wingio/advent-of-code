package solver.solutions

import solver.day
import util.collection.product

val Day06 = day(6) { input ->
    part1 {
        val (ops, nums) = input.lines()
            .map { it.trim().split("""\s+""".toRegex()) }
            .let { it.last() to it.dropLast(1).map { l -> l.map { s -> s.toLong() } } }

        ops.withIndex().sumOf { (i, op) -> nums.map { it[i] }.op(op.first()) }
    }

    part2 {
        val (ops, nums) = input.lines().let { it.last() to it.dropLast(1) }

        var currentNums = mutableListOf<Long>()
        var op = ' '
        var total = 0L

        for (i in 0..nums.maxOf { it.length }) {
            val c = ops.getOrNull(i)
            if (c != null && c != ' ') op = c
            val col = nums.mapNotNull { it.getOrNull(i) }.joinToString("")

            if (col.isBlank()) total += currentNums.op(op).also { currentNums.clear() }
            else currentNums += col.trim().toLong()
        }
        total
    }
}

fun List<Long>.op(op: Char): Long {
    return when(op) {
        '+' -> sum()
        '*' -> product()
        else -> error("Invalid operation: $op")
    }
}