package solver.solutions

import solver.day
import kotlin.math.abs

private const val DialNums = 100

val Day01 = day(1) { input ->
    val turns = input.lines().map {
        if (it.startsWith('R')) it.drop(1).toInt() else -it.drop(1).toInt()
    }

    part1(expected = 1118) {
        var current = 50
        turns.count { turn ->
            current += turn
            current % DialNums == 0
        }
    }

    part2(expected = 6289) {
        var current = 50
        var count = 0

        turns.forEach { turn ->
            val prev = current
            count += abs(turn) / DialNums
            current += turn % DialNums
            if (prev != 0 && current !in 1..<DialNums) count++
            current = (current + DialNums) % DialNums
        }
        count
    }
}