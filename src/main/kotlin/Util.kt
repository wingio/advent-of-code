import kotlin.io.path.Path
import kotlin.io.path.readText

private val numberSeparatorRx = "\\D+".toRegex()

fun <T> T?.print(transform: (T) -> Any? = { this }) = println(this?.run(transform))

fun readInput(day: Int, example: Boolean): String {
    return Path("./src/main/resources/Day$day${if (example) ".example" else ""}.txt")
        .readText()
        .replace("\r\n", "\n")
}

fun getNumbersFromLine(line: String): List<Int> {
    return line.split(numberSeparatorRx).map { it.toInt() }
}