fun main() {
    val initial = arrayOf(intArrayOf(1, 8, 2), intArrayOf(0, 4, 3), intArrayOf(7, 6, 5))
    val goal = arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 0))

    val x = 1
    val y = 0

    val puzzle = Puzzle()
    if (puzzle.isSolvable(initial)) {
        puzzle.solve(initial, goal, x, y)
    } else {
        println("The given initial is impossible to solve")
    }
}