import kotlin.time.measureTimedValue

fun day(day: Int, builder: Day.() -> Unit) {
    Day(day).apply(builder)()
}

class Day(
    private val day: Int
) {

    val input = readInput(day)

    private var partOne: () -> Any = {}
    private var partTwo: () -> Any = {}

    operator fun invoke() {
        println("""
            -+-=========================+-+{[ * ]}+-+=========================-+-
                                     Advent Of Code 2024
                                           Day ${day.toString().padStart(2, '0')}
            -+-------===================================================-------+-
        """.trimIndent())
        println("\n")

        val (result1,  time1) = measureTimedValue(partOne)
        println("""
            -+{[ Part 1 ]}=====================================================+-
            Result: $result1
            Took $time1
        """.trimIndent())

        println("\n")

        val (result2,  time2) = measureTimedValue(partTwo)
        println("""
            -+{[ Part 2 ]}=====================================================+-
            Result: $result2
            Took $time2
        """.trimIndent())
    }

    fun part1(block: () -> Any) {
        partOne = block
    }

    fun part2(block: () -> Any) {
        partTwo = block
    }

}