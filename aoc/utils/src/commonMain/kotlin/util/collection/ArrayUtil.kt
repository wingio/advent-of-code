package util.collection

/**
 * Creates a new array with elements of type [T]
 */
@Suppress("UNCHECKED_CAST")
fun <T> newArray(): Array<T?> = arrayOfNulls<Any?>(11) as Array<T?>

/**
 * Swap the positions of two elements in the array
 */
fun <T> Array<T>.swap(first: Int, second: Int) {
    val temp = get(first)
    set(first, get(second))
    set(second, temp)
}

fun <T> Array<T>.heapifyDown(index: Int, actualSize: Int, comparator: Comparator<in T>) {
    val leftChildIndex = index * 2 + 1
    if (leftChildIndex >= actualSize) {
        return
    }

    val rightChildIndex = leftChildIndex + 1

    val childIndex = when {
        rightChildIndex >= actualSize -> leftChildIndex
        comparator.compare(get(leftChildIndex), get(rightChildIndex)) < 0 -> leftChildIndex
        else -> rightChildIndex
    }

    if (comparator.compare(get(childIndex), get(index)) < 0) {
        swap(index, childIndex)
        heapifyDown(childIndex, actualSize, comparator)
    }
}

/**
 * Sorts the item at the specified [index] up the queue.
 */
fun <T> Array<T>.heapifyUp(index: Int, comparator: Comparator<in T>) {
    val parentIndex = if (index % 2 == 0) index / 2 - 1 else index / 2
    if (parentIndex < 0) {
        return
    }

    // Check if the parent is greater than the current item, if so we swap and loop from the parent
    if (comparator.compare(get(parentIndex), get(index)) > 0) {
        swap(index, parentIndex)
        heapifyUp(parentIndex, comparator)
    }
}