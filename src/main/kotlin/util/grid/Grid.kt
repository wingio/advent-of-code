package util.grid

/**
 * Represents a 2D coordinate plane.
 *
 * Each row must be the same length and there must be at least one row
 */
class Grid<T>(
    private val matrix: Array<Array<T>>
) {

    init {
        assert(matrix.distinctBy { it.size }.size == 1) { "All rows must be the same length" }
        assert(matrix.isNotEmpty()) { "Grid must have at least one row" }
    }

    private val data = buildMap {
        matrix.forEachIndexed { y: Int, row -> row.forEachIndexed { x: Int, item-> set(y to x, item) } }
    }.toMutableMap()

    /**
     * A map containing every point in this grid and its
     * corresponding item
     */
    val entries: Map<Point, T> = data

    val rows = matrix.indices
    val cols = matrix.first().indices

    var height = matrix.size
    var width = matrix.first().size

    val points: Set<Point> get() = data.keys
    val items: List<T> get() = data.values.toList()

    /**
     * Retrieves an entity at the specified [point]
     *
     * @throws IndexOutOfBoundsException If the specified [point] is out
     * of the bounds of this grid
     */
    operator fun get(point: Point): T {
        if (point !in this) throw IndexOutOfBoundsException("Point $point not within bounds of grid: ($height, $width)")
        return data[point]!!
    }

    /**
     * Retrieves an entity at the specified [point]
     * or null if it is out of bounds
     */
    fun getOrNull(point: Point): T? {
        return data[point]
    }

    /**
     * Retrieves an entity at the specified [point]
     * or defaults to [default] if it is out of bounds
     */
    fun getOrDefault(point: Point, default: T): T? {
        return data[point] ?: default
    }

    /**
     * Sets the item at a given [point]
     *
     * @throws IndexOutOfBoundsException If the specified [point] is out
     * of the bounds of this grid
     */
    operator fun set(point: Point, item: T) {
        if (point !in this) throw IndexOutOfBoundsException("Point $point not within bounds of grid: ($height, $width)")
        data[point] = item
    }

    /**
     * Checks whether the specified [point]
     * is within the bounds of this grid
     */
    operator fun contains(point: Point): Boolean {
        return point.y in rows && point.x in cols
    }

    override fun toString(): String {
        return data.keys.chunked(width).joinToString("\n") {
            it.map(::get).joinToString("")
        }
    }

    companion object {

        fun charGrid(str: String): Grid<Char> {
            return Grid(str.lines().map { it.toList().toTypedArray() }.toTypedArray())
        }

        fun intGrid(str: String): Grid<Int> {
            return Grid(str.lines().map { it.toList().map { c -> c.toString().toInt() }.toTypedArray() }.toTypedArray())
        }

    }

}

fun Grid(width: Int, height: Int) = Grid(Array(height) { Array(width) { '.' } })