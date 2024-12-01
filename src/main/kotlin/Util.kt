import kotlin.io.path.Path
import kotlin.io.path.readText

private val whitespaceRx = "\\s+".toRegex()

fun <T> T?.print(transform: (T) -> Any? = { this }) = println(this?.run(transform))

fun readInput(day: Int): String {
    return Path("./src/main/resources/Day$day.txt")
        .readText()
        .replace("\r\n", "\n") // We love Windows
}

fun getNumbersFromLine(line: String): List<Int> {
    return line.split(whitespaceRx).map { it.toInt() }
}