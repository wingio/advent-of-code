package solver.solutions

import solver.day
import util.collection.productOf
import util.collection.zipWithAllUnique
import util.point.Point3D
import util.point.distanceTo

val Day08 = day(8) { input ->
    part1(expected = 24360) {
        val (coords, circuits) = parse(input)

        for ((a, b) in coords.zipWithAllUnique().sortedBy { (a, b) -> a.distanceTo(b) }.take(1000)) circuits.link(a, b)
        circuits.sortedByDescending { it.size }.take(3).productOf { it.size }
    }

    part2(expected = 2185817796) {
        val (lines, circuits) = parse(input)
        val pairs = lines.zipWithAllUnique().sortedBy { (a, b) -> a.distanceTo(b) }.iterator()

        var out = 0L
        while (circuits.size >= 2) {
            val (a, b) = pairs.next()
            if (circuits.link(a, b)) out = a.x.toLong() * b.x.toLong()
        }
        out
    }
}

private fun parse(input: String) = input.lines()
    .map { Point3D.fromLine(it) }
    .let { points -> points to points.map { hashSetOf(it) }.toMutableList() }

private fun MutableList<HashSet<Point3D>>.link(a: Point3D, b: Point3D): Boolean {
    if (any { a in it && b in it }) return false
    val aCirc = first { a in it && b !in it }
    val bCirc = first { b in it && a !in it }
    bCirc.addAll(aCirc)
    remove(aCirc)
    return true
}