package puzzles

import Coords
import cardinals
import day
import north
import plus

// Day 6

val moves = mutableSetOf<Pair<Coords, Coords>>()
var obstacles = mutableSetOf<Coords>()
var turnCount = 0

fun main() = day(6) {
    val grid = input.lines()
    val startingPos = grid.indexOfFirst { it.contains("^") } to grid.first { it.contains("^") }.indexOf('^')

    part1 {
        grid.walkTo(startingPos)
        moves.map { it.first }.toSet().size + 1
    }

    part2 {
        moves.map { it.first }.toSet().forEach { grid.checkSpot(it) }
        obstacles.size
    }
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