package util.grid

import util.point.Direction
import util.point.Point
import util.point.extend

typealias Matrix<T> = Array<Array<T>>

operator fun <T> Matrix<T>.contains(point: Point): Boolean {
    return point.y in indices && point.x in (firstOrNull()?.indices ?: 0..0)
}

/**
 * Gets all points starting from the [origin] in the specified
 * [direction] with a specified [distance]
 */
fun <T> Grid<T>.reachFrom(origin: Point, direction: Direction, distance: Int): List<T>
        = origin.extend(direction, distance).mapNotNull(::getOrNull)

/**
 * Gets all points starting from the [origin] in the specified
 * [direction] with a specified [distance]
 */
fun Grid<Char>.reachFrom(origin: Point, direction: Direction, distance: Int): String
        = origin.extend(direction, distance).mapNotNull(::getOrNull).joinToString("")

/**
 * Iterates over each point in this grid
 */
inline fun <T> Grid<T>.forEachPoint(block: (Point) -> Unit) {
    points.forEach(block)
}

/**
 * Iterates over each point in this grid
 */
inline fun <T> Grid<T>.forEach(block: (Point, T) -> Unit) {
    points.forEach { block(it, get(it)) }
}

/**
 * Gets the coordinates of the first item
 * that meats the [predicate]
 */
inline fun <T> Grid<T>.pointOfFirst(predicate: (T) -> Boolean): Point {
    return points.first {
        predicate(get(it))
    }
}

/**
 * Gets the coordinates of the first item
 * that meats the [predicate]
 */
inline fun <T> Grid<T>.findPoint(predicate: (T) -> Boolean): Point? {
    return points.find {
        predicate(get(it))
    }
}

/**
 * Counts the number of items in the grid that match the given [predicate]
 */
inline fun <T> Grid<T>.count(predicate: (T) -> Boolean): Int = items.count(predicate)

/**
 * Counts the number of points in the grid that match the given [predicate]
 */
inline fun <T> Grid<T>.countPoints(predicate: (Point) -> Boolean): Int = points.count(predicate)