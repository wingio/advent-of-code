package solver.solutions

import solver.day
import util.collection.zipWithAllUnique
import util.point.Point
import kotlin.math.abs

val Day09 = day(9) { input ->
    val tiles = input.lines().map { Point.fromLine(it) }

    part1(expected = 4781546175) {
        tiles.zipWithAllUnique().maxOf { (a, b) -> (abs(b.y - a.y).toLong() + 1) * (abs(b.x - a.x).toLong() + 1) }
    }

    part2(expected = 1573359081) {
        tiles.windowed(2).forEach { (a, b) ->
            when {
                a.x == b.x -> {
                    val ys = a.y.coerceAtMost(b.y)..a.y.coerceAtLeast(b.y)
                    for (y in ys) cache[Point(a.x, y)] = true
                }
                a.y == b.y -> {
                    val xs = a.x.coerceAtMost(b.x)..a.x.coerceAtLeast(b.x)
                    for (x in xs) cache[Point(x, a.y)] = true
                }
            }
        }

        tiles.zipWithAllUnique().maxOf { (a, b) ->
            val Mx = maxOf(a.x, b.x)
            val mx = minOf(a.x, b.x)
            val My = maxOf(a.y, b.y)
            val my = minOf(a.y, b.y)

            if (!tiles.checkPoint(mx, my)
                || !tiles.checkPoint(mx, My)
                || !tiles.checkPoint(Mx, my)
                || !tiles.checkPoint(Mx, My)
            ) return@maxOf 0

            if ((mx..Mx step 1250).any { x -> !tiles.checkPoint(x, my) }) return@maxOf 0
            if ((my..My step 1250).any { y -> !tiles.checkPoint(mx, y) }) return@maxOf 0

            (Mx - mx.toLong() + 1) * (My - my.toLong() + 1)
        }
    }
}

private val cache = HashMap<Point, Boolean>()

fun List<Point>.checkPoint(x: Int, y: Int): Boolean {
    return cache.getOrPut(Point(x, y)) {
        val px = x.toLong() + 1
        val py = y.toLong() + 1

        var inside = false
        val n = size

        for (i in 0 until n) {
            val j = if (i == 0) n - 1 else i - 1

            val x1 = get(i).x.toLong(); val y1 = get(i).y.toLong()
            val x2 = get(j).x.toLong(); val y2 = get(j).y.toLong()

            if ((y1 > py) != (y2 > py) && px < (x2 - x1) * (py - y1) / (y2 - y1) + x1) inside = !inside
        }
        inside
    }
}