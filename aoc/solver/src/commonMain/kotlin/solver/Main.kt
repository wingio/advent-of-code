package solver

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.main
import com.github.ajalt.clikt.parameters.options.convert
import com.github.ajalt.clikt.parameters.options.help
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.int
import kotlinx.io.IOException
import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlinx.io.readString
import solver.solutions.*

val Days = listOf<Day>(
    Day01,
    Day02,
    Day03
)

fun main(args: Array<String>) {
    Main().main(args)
}

class Main : CliktCommand() {

    val day by option("--day", "-d", metavar = "day").int()
        .help("The Advent of Code day to solve")
        .convert { Days.getOrNull(it - 1) ?: fail("No solution found for day $it") }
        .required()

    val inputPath by option("--input", "-i", metavar = "path")
        .help("Path to a text file used for puzzle input")
        .convert {
            runCatching { SystemFileSystem.resolve(Path(it)) }
                .getOrNull() ?: fail("Could not resolve file at \"$it\"")
        }
        .required()

    override fun run() {
        try {
            println(
                """
                    -+-=========================+-+{[ * ]}+-+=========================-+-
                                             Advent Of Code 2025
                                                   Day ${day.day.toString().padStart(2, '0')}
                    -+-------===================================================-------+-
                """.trimIndent()
            )
            println()

            val input = SystemFileSystem.source(inputPath)
                .buffered()
                .readString()

            day.solve(input)
        } catch (e: IOException) {
            return println("Error: Input path is directory")
        }
    }

}