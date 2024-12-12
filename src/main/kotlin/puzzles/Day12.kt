package puzzles

import day
import util.grid.*

// Day 12

fun main() = day(12, example = false) {
    val map = Grid.charGrid(input)
    var regions: List<Pair<Char, MutableSet<Point>>> = listOf()

    part1 {
        val _regions = mutableListOf<Pair<Char, MutableSet<Point>>>()
        map.forEach { point, regionName ->
            map.findRegion(point, regionName, _regions, true)
        }

        regions = _regions.mergeGroups()

        val areas = regions.map { it.second.size }
        val perimeters = regions.map { (name, points) ->
            points.sumOf { point ->
                Directions.CARDINALS.map { d -> point + d }.map(map::getOrNull).count { it != name }
            }
        }

        areas.zip(perimeters).sumOf { (area, perimeter) -> area * perimeter }
    }

    part1 {
        val areas = regions.map { it.second.size }
        val sides = regions.map { (name, points) ->
            points.sumOf { point ->
                (Directions.CARDINALS + Directions.North).zipWithNext()
                    .filter { dirs ->
                        val (a, b) = dirs.toList().map(map::getOrNull)
                        val diagonal = map.getOrNull(point + dirs.first + dirs.second)

                        (a != name && b != name) or (a == name && b == name && diagonal != name)
                    }
                    .size
            }
        }

        areas.zip(sides).sumOf { (area, side) ->
            area * side
        }
    }
}

// My solution is so terrible I have to go back and patch up mistakes, oops
fun MutableList<Pair<Char, MutableSet<Point>>>.mergeGroups(): List<Pair<Char, MutableSet<Point>>> {
    val copy = this.toMutableList()
    forEach { p ->
        val (name, points) = p
        val matching = filter { (n, p) -> name == n && points.any { p.contains(it) } }
        matching.forEach {
            points.addAll(it.second)
            copy.remove(it)
        }
        copy.add(name to points)
    }
    return copy
}

private fun Grid<Char>.findRegion(point: Point, regionName: Char, regions: MutableList<Pair<Char, MutableSet<Point>>>, checkNeighbors: Boolean) {
    val neighbors = Directions.CARDINALS.reversed().map { point + it }
    if (checkNeighbors) {
        neighbors.forEach {
            getOrNull(it)?.let { r -> findRegion(it, r, regions, false) }
        }
    }

    val regionIdx = regions.indexOfFirst { (r, points) ->
        r == regionName && points.any { neighbors.contains(it) }
    }
    val region = (regions.getOrNull(regionIdx) ?: (regionName to mutableSetOf())).apply { second.add(point) }

    if (regionIdx == -1)
        regions.add(region)
    else
        regions[regionIdx] = region
}