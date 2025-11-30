# Advent of Code 2025
These are my solutions to the Advent of Code 2025 challenges, entirely in Kotlin Native.

## Info
All puzzle solutions can be found in the [solutions](/aoc/solver/src/commonMain/kotlin/solver/solutions) package.
Inputs are located in the [inputs](/inputs) directory, only examples are included per AOC guidelines.

### Build
1. Run `./gradlew :aoc:solver:build` to the build the solver cli.
2. Run the `aoc` program using `-d <day> -i <path to input>`
    - Linux: `aoc.kexe` is located in `aoc/solver/build/bin/linuxX64/aocReleaseExecutable`
    - Windows: `aoc.exe` is located in `aoc/solver/build/bin/mingwX64/aocReleaseExecutable`