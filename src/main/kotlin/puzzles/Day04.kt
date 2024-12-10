package puzzles

// From GridUtil

import day
import util.grid.*

// Day 4

private const val wordToFind = "XMAS"

fun main() = day(4) {
    val grid = Grid.charGrid(input)

    part1 {
        var total = 0

        grid.forEachPoint { (y, x) ->
            Directions.ALL.forEach { dir ->
                if (grid.reachFrom(y to x, dir, 4) == wordToFind)
                    total += 1
            }
        }

        total
    }

    part2 {
        val words = listOf("MAS", "SAM")
        var total = 0

        grid.forEachPoint { (y, x) ->
            if (
                grid.reachFrom((y to x) + Directions.NorthWest, Directions.SouthEast, 3) in words &&
                grid.reachFrom((y to x) + Directions.NorthEast, Directions.SouthWest, 3) in words
            ) total++
        }

        total
    }
}