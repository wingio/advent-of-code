package puzzles

import Coords
import Matrix
import contains
import day
import forEachCoord
import forEachItem

// Day 8

fun main() = day(8) {
    val freqMap: Matrix<Char> = input.lines().map { it.toList().toTypedArray() }.toTypedArray()
    val frequencies = mutableMapOf<Char, List<Coords>>()
    freqMap.forEachItem { coords, frequency ->
        if (frequency != '.') frequencies[frequency] = frequencies.getOrDefault(frequency, listOf()) + coords
    }

    val antiNodes = hashSetOf<Coords>()

    part1 {
        frequencies
            .forEach { (_, coords) ->
                coords.forEachIndexed { x, first ->
                    coords.subList(x + 1, coords.size).forEach { second ->
                        val dy = second.first - first.first
                        val dx = second.second - first.second

                        antiNodes.add(second.first + dy to second.second + dx)
                        antiNodes.add(first.first - dy to first.second - dx)
                    }
                }
            }


        antiNodes.count { it in freqMap }
    }

    part2 {
        frequencies
            .forEach { (_, coords) ->
                coords.forEachIndexed { x, first ->
                    coords.subList(x + 1, coords.size).forEach { second ->
                        freqMap.forEachCoord { (y, x) ->
                            if ((second.first - first.first).toDouble() / (second.second - first.second) // Get the slope between the two POIs
                                == (second.first - y).toDouble() / (second.second - x)) // Check if it is the same as the slope between the second POI and the current coordinate
                                antiNodes.add(y to x)
                        }
                    }
                }
            }

        antiNodes.addAll(frequencies.flatMap { it.value })
        antiNodes.count { it in freqMap }
    }
}