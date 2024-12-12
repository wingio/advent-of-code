package util

import kotlin.io.path.Path
import kotlin.io.path.readText

fun <T> T.print(transform: (T) -> Any? = { this }): T = println(this?.run(transform)).run { this@print }

fun readInput(day: Int, example: Boolean): String {
    return Path("./src/main/resources/Day$day${if (example) ".example" else ""}.txt")
        .readText()
        .replace("\r\n", "\n") // We love Windows <3
}