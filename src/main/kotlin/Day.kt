import kotlin.time.measureTimedValue

fun day(day: Int, builder: Day.() -> Unit) {
    Day(day).apply(builder)
}

class Day(
    day: Int
) {

    val input = readInput(day)

    init {
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

}