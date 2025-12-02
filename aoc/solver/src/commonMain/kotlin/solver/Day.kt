package solver

import util.print
import kotlin.time.measureTimedValue

fun day(day: Int, block: Day.(input: String) -> Unit): Day {
    return object : Day(day) {
        override fun solve(input: String) {
            try {
                block(input)
            } catch (e: Throwable) {
                println(e.message)
                e.printStackTrace()
            }
        }
    }
}

abstract class Day(
    val day: Int
) {

    abstract fun solve(input: String)

    fun part1(block: () -> Any) = solvePart(1, block)

    fun part2(block: () -> Any) = solvePart(2, block)

    private fun solvePart(part: Int, block: () -> Any) {
        measureTimedValue(block).print { (result, time) ->
            """
                -+{[ Part $part ]}=====================================================+-
                Result: $result
                Took $time
            """.trimIndent()
        }
        println()
    }

}