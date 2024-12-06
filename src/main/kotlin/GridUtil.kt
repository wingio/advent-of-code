typealias Coords = Pair<Int, Int>

val north = -1 to 0
val northWest = -1 to -1
val northEast = -1 to 1

val south = 1 to 0
val southWest = 1 to -1
val southEast = 1 to 1

val west = 0 to -1
val east = 0 to 1

val allDirections = listOf(north, northEast, northWest, south, southEast, southWest, east, west)
val cardinals = listOf(north, east, south, west)

operator fun Coords.plus(other: Coords) = first + other.first to second + other.second
operator fun Coords.times(other: Int) = first * other to second * other

fun Coords.extend(direction: Coords, count: Int) = List(count) { this + direction * it }

fun List<String>.reachFrom(origin: Coords, direction: Coords, count: Int)
        = origin.extend(direction, count).map { (y, x) -> getOrNull(y)?.getOrNull(x) }.filterNotNull().joinToString("")

inline fun List<String>.forEachCoord(block: (Coords) -> Unit) {
    forEachIndexed { y, row ->
        row.forEachIndexed { x, _ ->
            block(y to x)
        }
    }
}