package solver.solutions

import solver.day
import util.collection.merge
import util.collection.size

val Day05 = day(5) { input ->
    val sections = input.split("\n\n").map { it.lines() }
    val freshRanges = sections.first()
        .map { l -> l.split("-").let { it[0].toLong()..it[1].toLong() } }
        .sortedBy { it.first }

    part1(expected = 828) { sections[1].map { it.toLong() }.count { id -> freshRanges.any { id in it } } }
    part2(expected = 352681648086146) { freshRanges.merge().sumOf { it.size } }
}