package util.point

// Absolute Points

data class Point(
    val x: Int,
    val y: Int
) {

    operator fun plus(other: Direction) = Point(x + other.relativePoint.x, y + other.relativePoint.y)

    operator fun minus(other: Direction) = Point(x - other.relativePoint.x, y - other.relativePoint.y)

}

// Relative Points

open class Direction(val relativePoint: Point) {

    object North: Direction(Point(0, -1))
    object NorthEast: Direction(Point(1, -1))
    object East: Direction(Point(1, 0))
    object SouthEast: Direction(Point(1, 1))
    object South: Direction(Point(0, 1))
    object SouthWest: Direction(Point(-1, 1))
    object West: Direction(Point(-1, 0))
    object NorthWest: Direction(Point(-1, -1))

    operator fun times(int: Int) = Direction(Point(relativePoint.x * int, relativePoint.y * int))

    companion object {

        val ALL = listOf(North, NorthEast, East, SouthEast, South, SouthWest, West, NorthWest) // Exists for readability
        val CARDINALS = listOf(North, East, South, West)
        val WINDS = listOf(NorthWest, SouthWest, SouthEast, NorthEast)

    }

}

/**
 * Get the next direction by turning clockwise or counter-clockwise
 * according to [cw]
 *
 * @param cw Whether to turn clockwise, if false will turn counter-clockwise
 * @return The next direction
 */
fun Direction.turn(cw: Boolean): Direction
        = Direction.ALL[(Direction.ALL.indexOf(this) + if (cw) 1 else -1).mod(Direction.ALL.size)]

/**
 * Get the next cardinal direction by turning clockwise or counter-clockwise
 * according to [cw]
 *
 * @param cw Whether to turn clockwise, if false will turn counter-clockwise
 * @return The next cardinal direction
 */
fun Direction.cardinalTurn(cw: Boolean): Direction
        = Direction.CARDINALS[(Direction.CARDINALS.indexOf(this) + if (cw) 1 else -1).mod(Direction.CARDINALS.size)]