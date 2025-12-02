package solver.solutions

import solver.day

val Day02 = day(2) { input ->
    val ranges = input.split(",").map {
        val (start, end) = it.split("-").map { d -> d.toLong() }
        start..end
    }

    part1 {
        var x = 0L
        ranges.forEach { range ->
            for (id in range) {
                val idStr = id.toString()
                val len = idStr.length
                if (len % 2 != 0) continue
                if (idStr.take(len / 2) == idStr.takeLast(len / 2)) x += id
            }
        }
        x
    }

    part2 {
        var x = 0L
        ranges.forEach { range ->
            for (id in range) {
                val idStr = id.toString()

                for (i in 1..<idStr.length) {
                    if (idStr.length % i != 0) continue
                    if (idStr.take(i).repeat(idStr.length / i) == idStr) {
                        x += id
                        break
                    }
                }
            }
        }
        x
    }
}