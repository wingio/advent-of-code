package solver

import util.print
import kotlin.time.measureTimedValue

fun day(day: Int, block: Day.(input: String) -> Unit): Day {
    return object : Day(day) {
        override fun solve(input: String) {
            try {
                block(input)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }
}

abstract class Day(
    val day: Int
) {

    abstract fun solve(input: String)

    fun <T> part1(expected: T? = null, block: () -> T) = solvePart(1, expected, block)

    fun <T> part2(expected: T? = null, block: () -> T) = solvePart(2, expected, block)

    private fun <T> solvePart(part: Int, expected: T?, block: () -> T) {
        println("-+{[ Part $part ]}=====================================================+-")
        measureTimedValue(block).print { (result, time) ->
            buildString {
                appendLine("Result: $result")
                if (expected != null) {
                    append("Expected: $expected ")
                    if (result == expected) append("\u001b[1;32m✓") else append("\u001b[1;31m✗")
                    appendLine("\u001b[0m")
                }
                appendLine("Took $time")
            }
        }
        println()
    }

}