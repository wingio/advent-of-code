package puzzles

import Coords
import cardinals
import north
import plus
import readInput

private val input = readInput(day = 6)

val moves = mutableSetOf<Pair<Coords, Coords>>()
var obstacles = mutableSetOf<Coords>()
var turnCount = 0

fun main() {
    val grid = input.lines()
    val startingPos = grid.indexOfFirst { it.contains("^") } to grid.first { it.contains("^") }.indexOf('^')

    // Part 1
    grid.walkTo(startingPos)
    println("(Part 1) Unique guard positions: ${moves.map { it.first }.toSet().size + 1}")

    // Part 2
    moves.map { it.first }.toSet().forEach { grid.checkSpot(it) }
    println("(Part 2) Number of possible obstructions: ${obstacles.size}")
}

fun List<String>.canMoveToSpot(spot: Coords): Boolean? {
    return getOrNull(spot.first)?.getOrNull(spot.second)?.equals('#')?.not()
}

tailrec fun List<String>.walkTo(spot: Coords, direction: Coords = north) {
    if (canMoveToSpot(spot + direction) ?: return) {
        val move = spot to direction
        if (moves.contains(move)) error("Loop detected: $move") else moves.add(move)
        walkTo(spot + direction, direction)
    } else {
        turnCount++
        walkTo(spot, cardinals[turnCount % 4])
    }
}

fun List<String>.checkSpot(spot: Coords) {
    val modifiedGrid = map { it.toMutableList() }
    val lookSpot = modifiedGrid.getOrNull(spot.first)?.getOrNull(spot.second) ?: return
    if (lookSpot == '#' || lookSpot == '^') return
    modifiedGrid[spot.first][spot.second] = '#'
    val newGrid = modifiedGrid.map { it.joinToString("") }
    turnCount = 0; moves.clear()

    try {
        newGrid.walkTo(newGrid.indexOfFirst { it.contains("^") } to newGrid.first { it.contains("^") }.indexOf('^'))
    } catch (e: Throwable) {
        obstacles.add(spot)
    }
}