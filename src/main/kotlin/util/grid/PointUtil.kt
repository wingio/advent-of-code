package util.grid

import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Gets all points in a particular [direction]
 * with a distance of [count].
 *
 * Note that any of these points may be out of bounds.
 */
fun Point.extend(direction: Direction, count: Int) = List(count) { this + direction * it }

/**
 * Gets the distance between this and another [Point] along axes at right angles.
 */
fun Point.manhattanDistance(other: Point) = abs(x - other.x) + abs(y - other.y)

/**
 * Gets the straight line distance between this and another [Point].
 */
fun Point.euclideanDistance(other: Point) = sqrt((x.toFloat() - other.x).pow(2) + (y.toFloat() - other.y).pow(2))

/**
 * Gets all the neighboring points in the [cardinal directions][Directions.CARDINALS].
 *
 * Note that any of these points may be out of bounds.
 */
fun Point.cardinalNeighbors() = Directions.CARDINALS.map { this + it }

/**
 * Gets all the neighboring points in all directions.
 *
 * Note that any of these points may be out of bounds.
 */
fun Point.allNeighbors() = Directions.ALL.map { this + it }