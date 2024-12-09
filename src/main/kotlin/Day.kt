import kotlin.time.measureTimedValue

fun day(day: Int, example: Boolean = true, builder: Day.() -> Unit) {
    Day(day, example).apply(builder)
}

class Day(
    day: Int,
    example: Boolean
) {

    val input = readInput(day, example)

    init {
        if (example) printWarning("Using example input")

        println("""
            -+-=========================+-+{[ * ]}+-+=========================-+-
                                     Advent Of Code 2024
                                           Day ${day.toString().padStart(2, '0')}
            -+-------===================================================-------+-
        """.trimIndent())
        println("\n")
    }

    fun part1(block: () -> Any) {
        measureTimedValue(block).print { (result, time) ->
            """
                -+{[ Part 1 ]}=====================================================+-
                Result: $result
                Took $time
            """.trimIndent()
        }
        println()
    }

    fun part2(block: () -> Any) {
        measureTimedValue(block).print { (result, time) ->
            """
                -+{[ Part 2 ]}=====================================================+-
                Result: $result
                Took $time
            """.trimIndent()
        }
    }

    fun printWarning(warning: String) {
        val hash = "#".repeat(warning.length + 25)
        println("\u001b[1;30;43m $hash \u001b[0m")
        println("\u001b[1;30;43m ##  !!  WARNING: ${warning.uppercase()}  !!  ## \u001b[0m")
        println("\u001b[1;30;43m $hash \u001b[0m")
        println()
    }

}