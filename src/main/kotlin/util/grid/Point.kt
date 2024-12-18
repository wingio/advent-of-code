package util.grid

// Absolute Points

typealias Point = Pair<Int, Int>

val Point.x get() = second
val Point.y get() = first

operator fun Point.plus(other: Pair<Int, Int>) = y + other.y to x + other.x
operator fun Point.minus(other: Pair<Int, Int>) = y - other.y to x - other.x
operator fun Point.times(int: Int) = y * int to x * int

fun Point.extend(direction: Direction, count: Int) = List(count) { this + direction * it }


// Relative Points

typealias Direction = Pair<Int, Int>

/**
 * Get the next direction by turning clockwise or counter-clockwise
 * according to [cw]
 *
 * @param cw Whether to turn clockwise, if false will turn counter-clockwise
 * @return The next direction
 */
fun Direction.turn(cw: Boolean): Direction
        = Directions.ALL[(Directions.ALL.indexOf(this) + if (cw) 1 else -1).mod(Directions.ALL.size)]

/**
 * Get the next cardinal direction by turning clockwise or counter-clockwise
 * according to [cw]
 *
 * @param cw Whether to turn clockwise, if false will turn counter-clockwise
 * @return The next cardinal direction
 */
fun Direction.cardinalTurn(cw: Boolean): Direction
        = Directions.CARDINALS[(Directions.CARDINALS.indexOf(this) + if (cw) 1 else -1).mod(Directions.CARDINALS.size)]

object Directions {

    val North = -1 to 0
    val NorthEast = -1 to 1
    val East = 0 to 1
    val SouthEast = 1 to 1
    val South = 1 to 0
    val SouthWest = 1 to -1
    val West = 0 to -1
    val NorthWest = -1 to -1

    val ALL = listOf(North, NorthWest, West, SouthWest, South, SouthEast, East, NorthEast)
    val CARDINALS = listOf(North, East, South, West)
    val WINDS = listOf(NorthWest, SouthWest, SouthEast, NorthEast)

}