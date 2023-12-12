import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.abs

val absQ = PriorityQueue<Element>()

fun main() {
    BufferedReader(InputStreamReader(System.`in`))
        .use { reader ->
            val N = reader.readLine().toInt()
            for (i in 1..N) {
                val num = reader.readLine().toInt()
                if (num == 0) {
                    absQ.printAndPoll()
                    continue
                }
                absQ.add(Element(abs(num), num))
            }
        }
}

class Element(val absNum: Int, val ordinaryNum: Int) : Comparable<Element> {

    override fun compareTo(other: Element): Int {
        if (absNum < other.absNum) {
            return -1
        }
        if (absNum > other.absNum) {
            return 1
        }
        if (ordinaryNum < other.ordinaryNum) {
            return -1
        }
        return 1
    }
}

fun PriorityQueue<Element>.printAndPoll() {
    if (this.isEmpty()) {
        println(0)
        return
    }
    println(this.poll().ordinaryNum)
}
