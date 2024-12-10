package puzzles

import day
import util.grid.*

// Day 8

fun main() = day(8) {
    val freqMap = Grid.charGrid(input)
    val frequencies = mutableMapOf<Char, List<Point>>()
    freqMap.forEach { point, frequency ->
        if (frequency != '.') frequencies[frequency] = frequencies.getOrDefault(frequency, listOf()) + point
    }

    val antiNodes = hashSetOf<Point>()

    part1 {
        frequencies
            .forEach { (_, coords) ->
                coords.forEachIndexed { x, first ->
                    coords.subList(x + 1, coords.size).forEach { second ->
                        val dy = second.y - first.y
                        val dx = second.x - first.x

                        antiNodes.add(second.y + dy to second.x + dx)
                        antiNodes.add(first.y - dy to first.x - dx)
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
                        freqMap.forEachPoint { (y, x) ->
                            if ((second.y - first.y).toDouble() / (second.x - first.x) // Get the slope between the two POIs
                                == (second.y - y).toDouble() / (second.x - x)) // Check if it is the same as the slope between the second POI and the current coordinate
                                antiNodes.add(y to x)
                        }
                    }
                }
            }

        antiNodes.addAll(frequencies.flatMap { it.value })
        antiNodes.count { it in freqMap }
    }
}