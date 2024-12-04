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

import readInput

// Day 4

private val input = readInput(day = 4)

const val wordToFind = "XMAS"

fun main() {
    part1()
    part2()
}

fun part1() {
    val rows = input.lines()
    var total = 0

    rows.forEachCoord { (y, x) ->
        allDirections.forEach { dir ->
            if (rows.reachFrom(y to x, dir, 4) == wordToFind)
                total += 1
        }
    }

    println("(Part 1) Total word appearances: $total")
}

fun part2() {
    val rows = input.lines()
    val words = listOf("MAS", "SAM")
    var total = 0

    rows.forEachCoord { (y, x) ->
        if (
            rows.reachFrom((y to x) + northWest, southEast, 3) in words &&
            rows.reachFrom((y to x) + northEast, southWest, 3) in words
        ) total++
    }

    println("(Part 2) X-MAS appearances: $total")
}