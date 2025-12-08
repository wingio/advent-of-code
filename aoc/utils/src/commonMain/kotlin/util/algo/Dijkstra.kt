package util.algo

import util.collection.PriorityQueue
import util.point.Direction
import util.grid.Grid
import util.point.Point
import util.point.cardinalTurn
import kotlin.comparisons.compareBy

/**
 * Solves this grid as a maze using Dijkstra's Algorithm
 *
 * @param start The start of the maze
 * @param end The end of the maze
 * @param wallChar The character used to indicate a wall, defaults to `#`
 *
 * @return The score for this solve, will be [Int.MAX_VALUE] if unsolvable
 */
fun Grid<Char>.dijkstra(
    start: Point,
    end: Point,
    wallChar: Char = '#'
): Pair<Int, Set<List<Point>>> {
    val queue = PriorityQueue<Node>(compareBy { it.score })

    queue.add(Node(0, listOf(start), Direction.East))

    var score = Int.MAX_VALUE
    val scores = mutableMapOf<Pair<Point, Direction>, Int>()
    val path = mutableSetOf<List<Point>>()

    while (!queue.isEmpty()) {
        val node = queue.poll() ?: break
        val key = node.end to node.direction

        if (node.end == end) { // We did it :)
            if (node.score <= score) score = node.score else break
            path.add(node.points)
        }

        if (scores.containsKey(key) && scores[key]!! <= node.score) continue // Don't revisit points with a worse score, should keep us out of loops
        scores[key] = node.score

        val next = node.end + node.direction
        if (next in this && this[next] != wallChar) queue.add(node.move()) // Move forwards if the next point is in bounds and isn't a wall

        // Turn so that we can actually check all decent routes
        queue.add(node.turn(cw = false))
        queue.add(node.turn(cw = true))
    }

    return score to path
}

/**
 * Represents a given point in a maze
 *
 * @param score The score assigned to this point
 * @param points The coordinates of the point
 * @param direction The direction to move to
 */
data class Node(
    val score: Int,
    val points: List<Point>,
    val direction: Direction
) {

    val end get() = points.last()

    /**
     * Turns the node by one cardinal direction
     *
     * @param cw Whether to turn clockwise, if false will turn counter-clockwise
     * @return A copy of this [Node] facing in the next cardinal direction
     */
    fun turn(cw: Boolean) = copy(score = score, direction = direction.cardinalTurn(cw))

    /**
     * Moves forward by 1, increasing the score
     *
     * @return A copy of this [Node] in the next space according to
     * its direction and with its score increased by 1
     */
    fun move() = copy(score = score + 1, points = points + (end + direction))

}