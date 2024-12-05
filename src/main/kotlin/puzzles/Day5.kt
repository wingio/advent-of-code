package puzzles

import print
import readInput

private val input = readInput(day = 5)

typealias Rule = Pair<Int, Int>

fun main() {
    val sections = input.split("\n\n")
    val orderRules = sections[0]
        .lines()
        .map { rule ->
            rule.split("|")
                .map { it.toInt() }
                .run { first() to last() }
        }

    val updatePages = sections[1]
        .lines()
        .map { pages ->
            pages
                .split(",")
                .map { it.toInt() }
        }

    // Part 1
    updatePages
        .sumOf { pages ->
            val rules = pages.getApplicableRules(orderRules) // Get all the rules that pertain to at least two included page #s
            if (rules.all { pages.satisfies(it) }) // Check if this page # list is sorted according to the rules
                pages[pages.lastIndex / 2] else 0
        }
        .print { "(Part 1) Sum of the middle page #s: $it" }

    // Part 2
    updatePages
        .filter { pages -> // Get incorrectly ordered page lists
            val rules = pages.getApplicableRules(orderRules)
            rules.any { !pages.satisfies(it) }
        }
        .map { pages ->
            pages
                .toMutableList() // Lets us sort in place
                .apply(pages.getApplicableRules(orderRules)) // Apply all the necessary rules
        }
        .sumOf { pages ->
            pages[pages.lastIndex / 2] // Middle page #
        }
        .print { "(Part 2) Sum of the fixed middle page #s: $it" }
}

fun List<Int>.getApplicableRules(allRules: List<Rule>): List<Rule> {
    return allRules.filter { contains(it.first) && contains(it.second) }
}

fun List<Int>.satisfies(rule: Rule): Boolean {
    val (before, after) = rule
    return indexOf(before) < indexOf(after)
}

fun Int.checkAgainst(other: Int, rules: List<Rule>): Int {
    val rule = rules.firstOrNull() { this in it.toList() && other in it.toList() } ?: return 0
    return if (listOf(this, other).satisfies(rule)) -1 else 1
}

fun MutableList<Int>.apply(rules: List<Rule>): List<Int> {
    sortWith { page1, page2 ->
        page1.checkAgainst(page2, rules)
    }
    return this
}