import java.util.*

class Puzzle {
    var dimension = 3
    var row = intArrayOf(1, 0, -1, 0)
    var col = intArrayOf(0, -1, 0, 1)

    fun calculateCost(initial: Array<IntArray>, goal: Array<IntArray>): Int {
        var count = 0
        val n = dimension

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
        return (x >= 0 && x < dimension && y >= 0 && y < dimension)
    }

    fun printPath(root: Node?) {
        if (root == null) {
            return
        }

        printMatrix(root.matrix)
        println()
    }

    fun newNode(matrix: Array<IntArray>, x: Int, y: Int, newX: Int, newY: Int, level: Int, parent: Node?): Node {
        val result = Node(matrix, parent, x, y)

        result.matrix[x][y] = result.matrix[x][y] + result.matrix[newX][newY]
        result.matrix[newX][newY] = result.matrix[x][y] - result.matrix[newX][newY]
        result.matrix[x][y] = result.matrix[x][y] - result.matrix[newX][newY]
        result.cost = Int.MAX_VALUE
        result.level = level
        result.x = newX
        result.y = newY

        return result
    }

    fun solve(initial: Array<IntArray>, goal: Array<IntArray>, x: Int, y: Int) {
        val pq = PriorityQueue<Node>(5000) { a, b -> (a.cost + a.level) - (b.cost + b.level) }
        val root = newNode(initial, x, y, x, y, 0, null)
        root.cost = calculateCost(initial, goal)
        pq.add(root)

        while (!pq.isEmpty()) {
            val min = pq.poll()

            if (min.cost == 0) {
                printPath(min)
                return
            }

            for (i in 0 until 3) {
                if (isSafe(min.x + row[i], min.y + col[i])) {
                    val child = newNode(min.matrix, min.x, min.y, min.x + row[i], min.y + col[i], min.level + 1, min)
                    child.cost = calculateCost(child.matrix, goal)
                    pq.add(child)
                }
            }
        }
    }
}