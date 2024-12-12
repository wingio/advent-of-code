package util.list

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
    var product = first()
    for (num in drop(1)) {
        product *= num
    }
    return product
}