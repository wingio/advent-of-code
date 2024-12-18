package puzzles

import day
import kotlin.math.pow

// Day 17

fun main() = day(17, example = false) {
    val (aRegister, program) = input.lines().run {
        subList(0, 3).map { l -> l.split(": ").last().toLong() }.first() to
                last().split(": ").last().split(",").map { it.toInt() }
    }

    part1 {
        program.runProgram(aRegister).joinToString(",")
    }

    part2 { // Credit to https://github.com/zt64
        var valid = listOf(0L)
        val suffixes = (1..16).map { program.takeLast(it) }

        for (i in 1..program.size) {
            val suffix = suffixes[i - 1]

            valid = buildList {
                valid.forEach { n ->
                    for (offset in 0..8) {
                        val a = (8L * n + offset)

                        if(program.runProgram(a) == suffix) add(a)
                    }
                }
            }
        }

        valid.min()
    }
}

fun List<Int>.runProgram(aReg: Long): List<Int> {
    val outputs = mutableListOf<Int>()
    val registers = mutableListOf(aReg, 0, 0)
    var instructionPointer = 0

    fun getCombo(operand: Int): Long {
        return when (operand) {
            in 0..3 -> operand.toLong()
            4 -> registers[0]
            5 -> registers[1]
            6 -> registers[2]
            else -> operand.toLong()
        }
    }

    while (instructionPointer in indices) {
        val op = this[instructionPointer]

        when (op) {
            0 -> { registers[0] = (registers[0] / 2.0.pow(getCombo(this[instructionPointer + 1]).toDouble())).toLong() } // adv
            1 -> { registers[1] = registers[1] xor this[instructionPointer + 1].toLong() }                               // bxl
            2 -> { registers[1] = getCombo(this[instructionPointer + 1]) % 8 }                                           // bst
            3 -> { if (registers[0] != 0L) { instructionPointer = this[instructionPointer + 1]; continue } }             // jnz
            4 -> { registers[1] = registers[1] xor registers[2] }                                                        // bxc
            5 -> { outputs.add((getCombo(this[instructionPointer + 1]) % 8).toInt()) }                                   // out
            6 -> { registers[1] = (registers[0] / 2.0.pow(getCombo(this[instructionPointer + 1]).toDouble())).toLong() } // bdv
            7 -> { registers[2] = (registers[0] / 2.0.pow(getCombo(this[instructionPointer + 1]).toDouble())).toLong() } // cdv
        }

        instructionPointer += 2
    }

    return outputs
}