package solver.solutions

import solver.day
import util.grid.*
import util.print
import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class)
val Day07 = day(7) { input ->
    part1(expected = 1662) {
        val manifold = Grid.charGrid(input)
        manifold.trackBeams(start = manifold.pointOfFirst { it == 'S' })
                .countPoints { manifold[it] == '^' && manifold.getOrNull(it.up) == '|' }
    }

    part2(expected = 40941112789504) {
        val manifold = Grid.charGrid(input)
        val cache = mutableMapOf<Point, Long>()

        fun search(point: Point): Long = cache.getOrPut(point) {
            when(manifold.getOrNull(point.down)) {
                '^' -> search(point.left) + search(point.right)
                '.' -> search(point.down)
                else -> 1
            }
        }
        search(manifold.pointOfFirst { it == 'S' })
    }
}

private fun Grid<Char>.trackBeams(start: Point): Grid<Char> {
    val queue = mutableListOf(start)

    while (queue.isNotEmpty()) {
        var point = queue.removeFirst()

        inner@ while (true) {
            point += Direction.South
            if ((getOrNull(point) ?: break@inner) == '^') {
                listOf(point.left, point.right)
                    .filter { p -> points.contains(p) && get(p) != '|' }
                    .forEach { queue.add(it); set(it, '|') }

                break@inner
            } else set(point, '|')
        }
    }
    return this
}