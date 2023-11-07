import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max
import kotlin.math.min

var N = 0
var L = 0
var R = 0
var X = 0
var problems: Array<Int> = Array(15) { 0 }
var ans = 0

fun main() {
    read()
    for (i in 0 until N) {
        pick(i, problems[i], 1, problems[i], problems[i])
    }
    println(ans)
}

fun read() {
    BufferedReader(InputStreamReader(System.`in`))
        .use { reader ->
            var line = reader.readLine().split(" ")
                .map { it.toInt() }
            N = line[0]
            L = line[1]
            R = line[2]
            X = line[3]
            problems = reader.readLine().split(" ")
                .map { it.toInt() }
                .toTypedArray()
        }
}

fun pick(idx: Int, hard: Int, pickedCnt: Int, largest: Int, minimal: Int) {
    if (hard > R) {
        return
    }
    if (pickedCnt >= 2 && hard >= L && largest - minimal >= X) {
        ans++
    }
    for (i in idx + 1 until N) pick(
        i,
        hard + problems[i],
        pickedCnt + 1,
        max(largest, problems[i]),
        min(minimal, problems[i])
    )
}
