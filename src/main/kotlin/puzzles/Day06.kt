package puzzles

import day
import util.grid.*

// Day 6

private val moves = mutableSetOf<Pair<Point, Direction>>()
private var obstacles = mutableSetOf<Point>()
private var turnCount = 0

fun main() = day(6) {
    val grid = Grid.charGrid(input)
    val startingPos = grid.pointOfFirst { it == '^' }

    part1 {
        grid.walkTo(startingPos)
        moves.map { it.first }.toSet().size + 1
    }

    part2 {
        moves.map { it.first }.toSet().forEach { grid.checkSpot(it) }
        obstacles.size
    }
}

private fun Grid<Char>.canMoveToSpot(spot: Point): Boolean? {
    return getOrNull(spot)?.equals('#')?.not()
}

private tailrec fun Grid<Char>.walkTo(spot: Point, direction: Direction = Directions.North) {
    if (canMoveToSpot(spot + direction) ?: return) {
        val move = spot to direction
        if (moves.contains(move)) error("Loop detected: $move") else moves.add(move)
        walkTo(spot + direction, direction)
    } else {
        turnCount++
        walkTo(spot, Directions.CARDINALS[turnCount % 4])
    }
}

private fun Grid<Char>.checkSpot(spot: Point) {
    val item = get(spot)
    if (item == '#' || item == '^') return
    set(spot, '#')
    turnCount = 0; moves.clear()

    try {
        walkTo(pointOfFirst { it == '^' })
    } catch (e: Throwable) {
        obstacles.add(spot)
    }
    set(spot, '.')
}