package puzzles

import day
import util.list.getLongsFromLine

// Day 11

fun main() = day(11) {
    val stones = getLongsFromLine(input)

    part1 {
        val stoneMap = mutableMapOf<Pair<Int, Long>, Long>()
        stones.sumOf { change(it, 0, 25, stoneMap) }
    }

    part2 {
        val stoneMap = mutableMapOf<Pair<Int, Long>, Long>()
        stones.sumOf { change(it, 0, 75, stoneMap) }
    }
}

private fun change(stone: Long, depth: Int, maxDepth: Int, cache: MutableMap<Pair<Int, Long>, Long>): Long {
    if(depth == maxDepth) return 1
    cache[depth to stone]?.let { return it }

    cache[depth to stone] = when {
        stone == 0L -> change(1, depth + 1, maxDepth, cache)
        stone.toString().length % 2 == 0 -> {
            val str = stone.toString()
            val firstHalf = str.substring(0..<str.length / 2).toLong()
            val secondHalf = str.substring(str.length / 2).toLong()
            change(firstHalf, depth + 1, maxDepth, cache) + change(secondHalf, depth + 1, maxDepth, cache)
        }
        else -> {
            change(stone * 2024, depth + 1, maxDepth, cache)
        }
    }

    return cache[depth to stone]!!
}