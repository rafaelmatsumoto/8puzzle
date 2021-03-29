fun main() {
    val initial = arrayOf(intArrayOf(1, 2, 3), intArrayOf(5, 6, 0), intArrayOf(7, 8, 4))
    val goal = arrayOf(intArrayOf(1, 2, 3), intArrayOf(5, 8, 6), intArrayOf(0, 7, 4))

    val x = 1
    val y = 2

    val puzzle = Puzzle()
    puzzle.solve(initial, goal, x, y)

}