data class Node(
    var matrix: Array<IntArray>,
    var parent: Node?,
    var x: Int = 0,
    var y: Int = 0,
    var cost: Int = 0,
    var level: Int = 0
)