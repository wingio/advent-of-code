package puzzles

import day
import util.grid.*

// Day 14

private val inputRx = "p=(\\d+),(\\d+) v=(-?\\d+),(-?\\d+)".toRegex()

fun main() = day(14) {
    val grid = Grid(101, 103) // Create grid that is 101 tiles wide and 103 tiles tall
    val robots = input.lines().map {
        val match = inputRx.matchEntire(it)!!
        Robot(match.groupValues[2].toInt() to match.groupValues[1].toInt(), match.groupValues[4].toInt() to match.groupValues[3].toInt())
    }

    part1 {
        val endPositions = mutableListOf<Point>()

        robots.forEach { endPositions.add(it.path(grid, 100)) }

        endPositions.count { it.y in top && it.x in left } *
        endPositions.count { it.y in top && it.x in right } *
        endPositions.count { it.y in bottom && it.x in left } *
        endPositions.count { it.y in bottom && it.x in right }
    }

    part2 {
        if (example) return@part2 "Example doesn't work on Part 2"

        var i = 1
        val endPositions = mutableSetOf<Point>()

        while (true) {
            robots.forEach { endPositions.add(it.path(grid, i)) }

            if (endPositions.size == robots.size) break
            endPositions.clear(); i++
        }

        // Just to see our beautiful tree :)
        endPositions.forEach { grid[it] = '#' }
        println(grid)

        i
    }
}

private fun Robot.path(grid: Grid<Char>, steps: Int): Point {
    val newY = (startPos.y + (velocity.y * steps)).mod(grid.height)
    val newX = (startPos.x + (velocity.x * steps)).mod(grid.width)

    return Point(newY, newX)
}

private val left = 0..<50
private val right = 51..<101

private val top = 0..<51
private val bottom = 52..<103

private data class Robot(
    val startPos: Point,
    val velocity: Direction
)