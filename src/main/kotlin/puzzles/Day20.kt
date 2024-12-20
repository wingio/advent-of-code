package puzzles

import day
import util.grid.*

// Day 20

fun main() = day(20) {
    val map = Grid.charGrid(input)
    val (start, end) = map.pointOfFirst { it == 'S' } to map.pointOfFirst { it == 'E' }

    val track = mutableListOf<Point>()
    val visited = mutableSetOf<Point>()
    val queue = ArrayDeque<Point>().also { it.add(start) }

    while (queue.isNotEmpty()) {
        val point = queue.removeFirst().also { track.add(it) }
        if (point == end) break
        if (!visited.add(point)) continue
        point.cardinalNeighbors().filter { map.getOrNull(it) != '#' && it !in visited }.forEach(queue::add)
    }

    part1 { track.findShortcuts(shortcutLimit = 2) }
    part2 { track.findShortcuts(shortcutLimit = 20) }
}

private fun List<Point>.findShortcuts(shortcutLimit: Int): Int {
    var shortcutCount = 0
    for (i in indices) {
        for (j in (i + 1)..<size) {
            val distance = this[i].manhattanDistance(this[j])
            if (distance <= shortcutLimit && j - i - distance >= 100) shortcutCount++
        }
    }
    return shortcutCount
}