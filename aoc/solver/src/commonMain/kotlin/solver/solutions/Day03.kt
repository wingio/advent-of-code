package solver.solutions

import platform.posix.pow
import solver.day

val Day03 = day(3) { input ->
    val banks = input.lines().map {
        it.toList().map { c -> c.digitToInt().toLong() }
    }

    part1(expected = 17445) {
        banks.sumOf { bank ->
            val max = bank.dropLast(1).max()
            val nextMax = bank.subList(bank.indexOfFirst { x -> x == max } + 1, bank.size).max()

            (max * 10) + nextMax
        }
    }

    part2(expected = 173229689350551) {
        banks.sumOf { bank ->
            var x = 0L
            var lastIndex = 0
            for (i in 11 downTo 0) {
                val max = bank.subList(lastIndex, bank.size - i).max()
                lastIndex += bank.drop(lastIndex).indexOfFirst { x -> x == max } + 1

                x += (max * pow(10.0, i.toDouble()).toLong())
            }
            x
        }
    }
}