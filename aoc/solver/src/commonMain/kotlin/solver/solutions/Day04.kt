package solver.solutions

import solver.day
import util.grid.Grid
import util.grid.Point
import util.grid.allNeighbors

val Day04 = day(4) { input ->
    val rolls = Grid.charGrid(input)

    part1(expected = 1376) { rolls.getRemovable().count() }
    part2(expected = 8587) { rolls.getRemovableCount() }
}

private tailrec fun Grid<Char>.getRemovableCount(count: Int = 0): Int {
    val removable = getRemovable().onEach { remove(it) }.takeIf { it.isNotEmpty() } ?: return count
    return getRemovableCount(count + removable.size)
}

private fun Grid<Char>.getRemovable(): List<Point>
    = points.filter { this[it] == '@' && it.allNeighbors().count { p -> getOrNull(p) == '@'} < 4 }