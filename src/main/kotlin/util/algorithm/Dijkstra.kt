package util.algorithm

import util.grid.*
import java.util.*

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
): Int {
    val queue = PriorityQueue<Node>(compareBy { it.score })

    queue.add(Node(0, start, Directions.East))

    var score = Int.MAX_VALUE
    val scores = mutableMapOf<Pair<Point, Direction>, Int>()

    while (queue.isNotEmpty()) {
        val node = queue.poll()
        val key = node.point to node.direction

        if (node.point == end) { // We did it :)
            score = node.score
            break
        }

        if (scores.containsKey(key) && scores[key]!! <= node.score) continue // Don't revisit points with a worse score, should keep us out of loops
        scores[key] = node.score

        val next = node.point + node.direction
        if (next in this && this[next] != wallChar) queue.add(node.move()) // Move forwards if the next point is in bounds and isn't a wall

        // Turn so that we can actually check all decent routes
        queue.add(node.turn(cw = false))
        queue.add(node.turn(cw = true))
    }

    return score
}

/**
 * Represents a given point in a maze
 *
 * @param score The score assigned to this point
 * @param point The coordinates (Y, X) of the point
 * @param direction The direction to move to
 */
data class Node(
    val score: Int,
    val point: Point,
    val direction: Direction
) {

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
    fun move() = copy(score = score + 1, point = point + direction)

}