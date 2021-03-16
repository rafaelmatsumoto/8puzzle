import java.util.ArrayList
import java.util.PriorityQueue

class Puzzle {
    var dimension = 3
    var row = intArrayOf(1, 0, -1, 0)
    var col = intArrayOf(0, -1, 0, 1)

    fun calculateCost(initial: Array<IntArray>, goal: Array<IntArray>): Int {
        var count = 0
        val n = initial.size

        for (i in 0 until n) {
            for (j in 0 until n) {
                if (initial[i][j] != 0 && initial[i][j] != goal[i][j]) {
                    count++
                }
            }
        }
        return count
    }

    fun printMatrix(matrix: Array<IntArray>) {
        matrix.let {
            for (i in it.indices) {
                for (j in it.indices) {
                    print("" + it[i][j] + " ")
                }
                println()
            }
        }
    }

    fun isSafe(x: Int, y: Int): Boolean {
        return (x in 0 until dimension && y >= 0 && y < dimension)
    }

    fun printPath(root: Node) {
        root.parent?.let {
            printPath(it.parent!!)
            printMatrix(root.matrix)
            println()
        }
    }

    fun isSolvable(matrix: Array<IntArray>): Boolean {
        var count = 0
        val array = ArrayList<Int>()

        for (i in matrix.indices) {
            for (j in matrix.indices) {
                array.add(matrix[i][j])
            }
        }

        val anotherArray = intArrayOf(array.size)

        array.toTypedArray()
        for (i in 0 until anotherArray.size - 1) {
            for (j in i + 1 until anotherArray.size) {
                if (anotherArray[i] != 0 && anotherArray[j] != 0 && anotherArray[i] > anotherArray[j]) {
                    count++
                }
            }
        }
        return count % 2 == 0
    }

    fun solve(initial: Array<IntArray>, goal: Array<IntArray>, x: Int, y: Int) {
        val pq = PriorityQueue<Node>(1000) { a, b -> (a.cost + a.level) - (b.cost + b.level) }
        val root = Node(initial, x, y, x, y, 0, null)
        root.cost = calculateCost(initial, goal)
        pq.add(root)

        while (!pq.isEmpty()) {
            val min = pq.poll()

            if (min.cost == 0) {
                printPath(min)
                return
            }

            for (i in 0..3) {
                if (isSafe(min.x + row[i], min.y + col[i])) {
                    val child = Node(min.matrix, min.x, min.y, min.x + row[i], min.y + col[i], min.level + 1, min)
                    child.cost = calculateCost(child.matrix, goal)
                    pq.add(child)
                }
            }
        }
    }
}