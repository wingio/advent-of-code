package puzzles

import day

// Day 9

private data class Block(val id: Int, val start: Int, val size: Int)
private data class Space(val start: Int, val size: Int)

fun main() = day(9, example = false) {
    val map = input.map { it.toString().toInt() }

    val spaces = mutableListOf<Space>()
    val blocks = mutableListOf<Block>()

    val fs = buildList {
        var id = 0
        map.forEachIndexed { i, size ->
            val start = this@buildList.size
            repeat(size) { add(if (i % 2 == 1) "." else id.toString()) }

            if (i % 2 == 0) { blocks.add(Block(id, start, size)); id++ }
            else { spaces.add(Space(start, size)) }
        }
    }

    part1 {
        val fsCopy = fs.toMutableList()
        var i = 0

        for (id in fs.reversed()) {
            val bit = fs.lastIndex - (i)
            if (id != ".") {
                val firstBlankIdx = fsCopy.indexOfFirst { it == "." }
                if (fsCopy.indexOfLast { it != "." } < firstBlankIdx) break
                if(firstBlankIdx != -1 && firstBlankIdx < bit) {
                    fsCopy[firstBlankIdx] = id
                    fsCopy[bit] = "."
                }
            }
            i++
        }

        fsCopy.hash()
    }

    part2 {
        val fsCopy = fs.toMutableList()

        for (block in blocks.reversed()) {
            val bSize = block.size
            val validSpaceIdx = spaces.indexOfFirst { it.start < block.start && block.size <= it.size }
            if (validSpaceIdx == -1) continue

            val validSpace = spaces[validSpaceIdx]
            spaces[validSpaceIdx] = validSpace.copy(size = validSpace.size - block.size, start = validSpace.start + block.size)

            for (i in validSpace.start..<validSpace.start + bSize) fsCopy[i] = block.id.toString()
            for (j in block.start..<block.start + bSize) fsCopy[j] = "."
        }

        fsCopy.hash()
    }
}

fun List<String>.hash(): Long {
    var hash = 0L
    forEachIndexed { index, id ->
        if (id != ".") hash += index * id.toInt()
    }
    return hash
}