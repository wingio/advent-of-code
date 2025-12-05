package util.collection

val IntRange.size get() = last - first + 1
val LongRange.size get() = last - first + 1

fun IntRange.overlaps(other: IntRange) = first <= other.last
fun LongRange.overlaps(other: LongRange) = first <= other.last

/**
 * Merges any and all ranges that overlap
 */
fun Collection<IntRange>.merge(): List<IntRange> {
    if (isEmpty()) return emptyList()
    return buildList {
        val sorted = this@merge.sortedBy { it.first }
        var curr = sorted.first()

        for (range in sorted.drop(1)) {
            when {
                range.overlaps(curr) -> curr = curr.first..maxOf(range.last, curr.last)
                else -> add(curr).also { curr = range }
            }
        }

        add(curr)
    }
}

/**
 * Merges any and all ranges that overlap
 */
fun Collection<LongRange>.merge(): List<LongRange> {
    if (isEmpty()) return emptyList()
    return buildList {
        val sorted = this@merge.sortedBy { it.first }
        var curr = sorted.first()

        for (range in sorted.drop(1)) {
            when {
                range.overlaps(curr) -> curr = curr.first..maxOf(range.last, curr.last)
                else -> add(curr).also { curr = range }
            }
        }

        add(curr)
    }
}