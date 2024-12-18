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

    part2 { // Need to bruteforce eventually
        var aReg = 100000000000000
        val inc = 1
        val last = 10

        while (false) {
            val out = program.runProgram(aReg)
            if (out == program) break else aReg -= inc
//            if (out.take(6) == program.take(6)) break else aReg += inc
//            if (out.size == program.size) {
//                if (out.takeLast(last) == program.takeLast(last)) {
//                    return@part2 aReg
//                }
//            }
//            aReg += inc
        }

        aReg
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