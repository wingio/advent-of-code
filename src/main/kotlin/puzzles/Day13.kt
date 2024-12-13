package puzzles

import day
import util.grid.Direction
import util.grid.Point

// Day 13

private val gameRx = "X[+=](\\d+), Y[+=](\\d+)".toRegex()

fun main() = day(13) {
    val games = input.split("\n\n").map {
        val p = it.split("\n").map { line ->
            val m = gameRx.find(line)!!
            m.groupValues[1].toInt() to m.groupValues[2].toInt()
        }

        Game(p[0].first to p[0].second, p[1].first to p[1].second, p[2].first to p[2].second)
    }

    part1 {
        games.sumOf { it.play() }
    }

    part2 {
        games.sumOf { it.play(axisIncrease = 10000000000000) }
    }
}

private fun Game.play(axisIncrease: Long = 0L): Long {
    val xPrize = prizeLocation.first + axisIncrease
    val yPrize = prizeLocation.second + axisIncrease

    val (aX, aY) = buttonA
    val (bX, bY) = buttonB

    val constant = ((aX * bY) + (-bX * aY))
    val aPresses = ((bY * xPrize) + (-bX * yPrize)) / constant
    val bPresses = ((-aY * xPrize) + (aX * yPrize)) / constant

    return if ((aPresses * aX) + (bPresses * bX) == xPrize && (aPresses * aY) + (bPresses * bY) == yPrize)
                aPresses * 3 + bPresses
           else
                0
}

private data class Game(
    val buttonA: Direction,
    val buttonB: Direction,
    val prizeLocation: Point
)