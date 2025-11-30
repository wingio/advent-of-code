@file:Suppress("UNCHECKED_CAST")

package util.collection

/*
 * Implementation borrowed from badoo/reaktive, licensed under Apache 2.0
 * Original source: https://github.com/badoo/Reaktive/blob/9d6cb63084bdd5d1fa3261e597b5ccc3d8e9bb3b/reaktive/src/commonMain/kotlin/com/badoo/reaktive/utils/queue/PriorityQueue.kt
 */
class PriorityQueue<T>(
    private val comparator: Comparator<in T>
): Collection<T> {

    private var array: Array<T?>? = null
    private var _size: Int = 0

    override val size: Int get() = _size

    /**
     * Retrieve the head of the queue without removing it, if it exists
     */
    fun peek(): T? =
        array?.takeUnless { isEmpty() }?.get(0)

    /**
     * Adds an [item] into the queue
     *
     * Note that the item will not always be placed
     * at the tail of the queue, instead being sorted
     * by priority.
     */
    fun add(item: T) {
        var arr: Array<T?>? = array
        if (arr == null) {
            arr = newArray()
        } else if (_size == arr.size) {
            arr = arr.copyOf(_size * 2)
        }
        array = arr

        val lastIndex = _size++
        arr[lastIndex] = item

        (arr as Array<T>).heapifyUp(lastIndex, comparator)
    }

    /**
     * Retrieves the item at the head of the queue, removing it
     * in the process.
     */
    fun poll(): T? {
        val arr = array
        if ((arr == null) || isEmpty()) {
            return null
        }

        val lastIndex = --_size
        val item = arr[0]
        arr[0] = arr[lastIndex]
        arr[lastIndex] = null
        (arr as Array<T>).heapifyDown(0, _size, comparator)

        return item
    }

    /**
     * Empty the queue, removing all items
     */
    fun clear() {
        array = null
        _size = 0
    }

    override fun iterator(): Iterator<T> {
        return object : Iterator<T> {

            private var index = 0

            override fun hasNext() = index < _size

            override fun next(): T {
                val arr = array?.takeIf { index < _size } ?: throw NoSuchElementException()
                return arr[index++] as T
            }

        }
    }

    override fun isEmpty() = _size == 0

    override fun contains(element: T) = array?.contains(element) ?: false

    override fun containsAll(elements: Collection<T>): Boolean {
        val arr = array ?: return false
        return elements.all { arr.contains(it) }
    }

}