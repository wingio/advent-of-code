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