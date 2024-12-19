package puzzles

import day
import util.algorithm.dijkstra
import util.grid.*

// Day 18

fun main() = day(18) {
    val memory = Grid(71, 71)
    val fallingBytes: List<Point> = input.lines().map {
        it.split(",").map { c -> c.toInt() }.let { c -> c.last() to c.first() }
    }

    val end: Point = memory.rows.last to memory.cols.last
    fallingBytes.take(1024).forEach {
        memory[it] = '#'
    }

    part1 { memory.dijkstra(Point(0, 0), end) }

    part2 {
        fallingBytes.drop(1024).forEach {
            memory[it] = '#'
            if (memory.dijkstra(Point(0, 0), end) == Int.MAX_VALUE) return@part2 "${it.x},${it.y}"
        }
    }
}