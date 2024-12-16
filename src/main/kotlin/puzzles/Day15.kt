package puzzles

import day
import util.grid.*

// Day 15

fun main() = day(15, example = true) {
    val (grid, moves: List<Direction>) = input.split("\n\n").run {
        Grid.charGrid(first()) to
                last().replace("\n", "").toCharArray().map {
                    when (it) {
                        '^' -> Directions.North
                        '>' -> Directions.East
                        'v' -> Directions.South
                        else -> Directions.West
                    }
                }
    }

    part1 {
        val map = Grid.charGrid(grid.toString()) // Make a copy :)
        var pos = map.pointOfFirst { it == '@' }

        for (move in moves) {
            val nextTile = map.getOrNull(pos + move)
            if (nextTile == null || nextTile == '#') continue // Walls

            if (nextTile == '.') { // Empty spaces
                map[pos] = '.'
                pos += move
                map[pos] = '@'
                continue
            }

            // Object in front of the robot
            val ray = pos.extend(move, maxOf(map.width, map.height)).toList()

            // Check if there is a wall in front of the next nearest space
            val nearestWallIdx = ray.indexOfFirst { map.getOrNull(it) == '#' }
            val nearestSpaceIdx = ray.indexOfFirst { map.getOrNull(it) == '.' }
            if (nearestWallIdx < nearestSpaceIdx) continue

            // We can move the box to the next empty space
            val nearestSpace = ray.firstOrNull { map.getOrNull(it) == '.' } ?: continue

            map[nearestSpace] = 'O'
            map[pos] = '.'
            pos += move
            map[pos] = '@'
        }

        // TODO: Write utility for this
        println(map)
        map.entries
            .filter { it.value == 'O' }
            .entries.sumOf { (point, _) -> point.toGPS() }
    }
}

private fun Point.toGPS(): Int {
    return 100 * y + x
}