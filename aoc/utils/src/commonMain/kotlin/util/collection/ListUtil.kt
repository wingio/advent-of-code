package util.collection

private val numberSeparatorRx = "\\D+".toRegex()
private val charSeparatorRx = "[\\W_]+".toRegex()

/**
 * Splits a line into [Int]s using any non-digit character
 */
fun getIntsFromLine(line: String): List<Int> {
    return line.split(numberSeparatorRx).map { it.toInt() }
}

/**
 * Splits a line into [Long]s using any non-digit character
 */
fun getLongsFromLine(line: String): List<Long> {
    return line.split(numberSeparatorRx).map { it.toLong() }
}

/**
 * Splits a line into [Char]s using any non-word character
 */
fun getCharsFromLine(line: String): List<Char> {
    return line.split(charSeparatorRx).map { it.toCharArray().first() }
}

/**
 * Splits a line into [String]s using any non-word character
 */
fun getStringsFromLine(line: String): List<String> {
    return line.split(charSeparatorRx)
}

/**
 * Multiplies all numbers in this sequence
 */
fun Collection<Long>.product(): Long {
    if (isEmpty()) return 0
    var product = first()
    for (num in drop(1)) {
        product *= num
    }
    return product
}

/**
 * Multiplies all numbers in this sequence
 */
fun Collection<Int>.product(): Long {
    if (isEmpty()) return 0
    var product = first().toLong()
    for (num in drop(1)) {
        product *= num
    }
    return product
}

inline fun <T> Collection<T>.productOf(block: (T) -> Int): Long {
    if (isEmpty()) return 0
    var product = block(first()).toLong()
    for (item in drop(1)) {
        product *= block(item)
    }
    return product
}

// Yeah, I stole this from https://codeberg.org/eagely/adventofcode-kotlin/src/branch/main/src/main/kotlin/solutions/y2025/Day8.kt
fun <T> List<T>.zipWithAllUnique(): List<Pair<T, T>> {
    val result = mutableListOf<Pair<T, T>>()
    val seenPairs = mutableSetOf<Pair<T, T>>()

    for (i in this.indices) {
        for (j in i + 1 until this.size) {
            val pair = Pair(this[i], this[j])
            if (pair !in seenPairs) {
                result.add(pair)
                seenPairs.add(pair)
                seenPairs.add(pair.second to pair.first) // Add reverse pair as well
            }
        }
    }
    return result
}