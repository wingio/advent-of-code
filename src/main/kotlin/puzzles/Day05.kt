package puzzles

import day

// Day 5

private typealias Rule = Pair<Int, Int>

fun main() = day(5) {
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

    part1 {
        updatePages
            .sumOf { pages ->
                val rules = pages.getApplicableRules(orderRules) // Get all the rules that pertain to at least two included page #s
                if (rules.all { pages.satisfies(it) }) // Check if this page # list is sorted according to the rules
                    pages[pages.lastIndex / 2] else 0
            }
    }

    part2 {
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
    }
}

private fun List<Int>.getApplicableRules(allRules: List<Rule>): List<Rule> {
    return allRules.filter { contains(it.first) && contains(it.second) }
}

private fun List<Int>.satisfies(rule: Rule): Boolean {
    val (before, after) = rule
    return indexOf(before) < indexOf(after)
}

private fun Int.checkAgainst(other: Int, rules: List<Rule>): Int {
    val rule = rules.firstOrNull() { this in it.toList() && other in it.toList() } ?: return 0
    return if (listOf(this, other).satisfies(rule)) -1 else 1
}

private fun MutableList<Int>.apply(rules: List<Rule>): List<Int> {
    sortWith { page1, page2 ->
        page1.checkAgainst(page2, rules)
    }
    return this
}