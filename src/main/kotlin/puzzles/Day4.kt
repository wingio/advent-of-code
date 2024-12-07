package puzzles

// From GridUtil
import allDirections
import northEast
import northWest
import southEast
import southWest
import plus
import reachFrom
import forEachCoord

import day

// Day 4

const val wordToFind = "XMAS"

fun main() = day(4) {
    val rows = input.lines()

    part1 {
        var total = 0

        rows.forEachCoord { (y, x) ->
            allDirections.forEach { dir ->
                if (rows.reachFrom(y to x, dir, 4) == wordToFind)
                    total += 1
            }
        }

        total
    }

    part2 {
        val words = listOf("MAS", "SAM")
        var total = 0

        rows.forEachCoord { (y, x) ->
            if (
                rows.reachFrom((y to x) + northWest, southEast, 3) in words &&
                rows.reachFrom((y to x) + northEast, southWest, 3) in words
            ) total++
        }

        total
    }
}