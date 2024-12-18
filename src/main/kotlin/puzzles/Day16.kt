package puzzles

import day
import util.grid.*
import java.util.PriorityQueue

// Day 16

fun main() = day(16) {
    val maze = Grid.charGrid(input)

    part1 { maze.dijkstra().first }
    part2 { maze.dijkstra().second.size }
}

private fun Grid<Char>.dijkstra(): Pair<Int, Set<Point>> {
    val queue = PriorityQueue<Path>(compareBy { it.score })
    val start = pointOfFirst { it == 'S' }
    val end = pointOfFirst { it == 'E' }

    queue.add(Path(0, listOf(start), Directions.East))

    var score = Int.MAX_VALUE
    val scores = mutableMapOf<Pair<Point, Direction>, Int>()
    val seats = mutableSetOf<Point>()

    while (queue.isNotEmpty()) {
        val node = queue.poll()
        val key = node.end to node.direction

        if (node.end == end) { // We did it :)
            if (node.score <= score) score = node.score else break
            seats.addAll(node.points)
        }

        if (scores.containsKey(key) && scores[key]!! < node.score) continue // Don't revisit points with a worse score, should keep us out of loops
        scores[key] = node.score

        if (this[node.end + node.direction] != '#') queue.add(node.move()) // As long as there isn't a wall, we can proceed forwards
        queue.add(node.turn(cw = false))
        queue.add(node.turn(cw = true))
    }

    return score to seats
}

private data class Path(
    val score: Int,
    val points: List<Point>,
    val direction: Direction
) {

    val end get() = points.last()
    fun turn(cw: Boolean) = copy(score = score + 1000, direction = direction.cardinalTurn(cw))
    fun move() = copy(score = score + 1, points = points + (end + direction))

}