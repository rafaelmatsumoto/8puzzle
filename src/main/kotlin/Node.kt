class Node(var matrix: Array<IntArray>, var x: Int, var y: Int, newX: Int, newY: Int, var level: Int, var parent: Node?) {
    var cost = 0

    init {
        for (i in matrix.indices) {
            matrix[i] = matrix[i].clone()
        }

        matrix[x][y] = matrix[x][y] + matrix[newX][newY]
        matrix[newX][newY] = matrix[x][y] - matrix[newX][newY]
        matrix[x][y] = matrix[x][y] - matrix[newX][newY]

        cost = Integer.MAX_VALUE
        x = newX
        y = newY
    }
}