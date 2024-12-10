package puzzles

import day
import util.grid.Directions
import util.grid.Grid
import util.grid.Point
import util.grid.plus

// Day 10

fun main() = day(10) {
    val map = Grid.intGrid(input)
    val trailheads = map.entries.filter { (_, height) -> height == 0 }.keys

    part1 {
        trailheads.sumOf {
            val visited = mutableListOf<Point>()
            map.route(it, visited)
            visited.toSet().count { pos -> map[pos] == 9 }
        }
    }

    part2 {
        trailheads.sumOf {
            val visited = mutableListOf<Point>()
            map.route(it, visited)
            visited.count { pos -> map[pos] == 9 }
        }
    }
}

fun Grid<Int>.route(start: Point, visited: MutableList<Point>) {
    val surrounding: List<Point> = Directions.CARDINALS.map { start + it }
    visited.add(start)
    if (get(start) == 9) return

    surrounding
        .filter { getOrNull(it) == get(start) + 1 }
        .forEach { route(it, visited) }
}