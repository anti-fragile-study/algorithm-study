import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val solve = Solve()
    solve.run()
}

internal class Solve {
    internal inner class Node(val node: Int, val weight: Int)

    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var read: Array<String>
    private lateinit var check: BooleanArray
    private var N = 0
    private var R = 0
    private var gigaNode = 0
    private var edges: ArrayList<ArrayList<Node>>? = null

    fun run() {
        input()
        println(getPillar(R).toString() + " " + getBranch(gigaNode))
    }

    private fun getPillar(idx: Int): Int {
        check[idx] = true
        gigaNode = idx
        if (idx == R && edges!![idx].size > 1 || idx != R && edges!![idx].size != 2 || N == 1) return 0
        var nextNode = edges!![idx][0]
        nextNode = if (check[nextNode.node] == true) edges!![idx][1] else nextNode
        return getPillar(nextNode.node) + nextNode.weight
    }

    private fun getBranch(idx: Int): Int {
        check[idx] = true
        var ret = 0
        for (i in edges!![idx].indices) {
            val nextNode = edges!![idx][i]
            if (check[nextNode.node]) continue
            ret = Math.max(ret, getBranch(nextNode.node) + nextNode.weight)
        }
        return ret
    }

    private fun input() {
        read = br.readLine().split(" ").toTypedArray()
        N = read[0].toInt()
        R = read[1].toInt()
        edges = ArrayList(N + 5)
        check = BooleanArray(N + 5)
        for (i in 0 until N + 5) edges!!.add(ArrayList())
        for (i in 1 until N) {
            read = br.readLine().split(" ").toTypedArray()
            val a = read[0].toInt()
            val b = read[1].toInt()
            val d = read[2].toInt()
            edges!![a].add(Node(b, d))
            edges!![b].add(Node(a, d))
        }
    }
}
