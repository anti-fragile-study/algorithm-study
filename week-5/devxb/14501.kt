import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

var N = 0
var ans = 0
val arr: Array<Pair<Int, Int>> = Array(16) { 0 to 0 }
val dp: Array<Int> = Array(16) { 0 }

fun main() {
    readLine()
    recur(1)
    println(ans)
}

fun readLine() {
    BufferedReader(InputStreamReader(System.`in`)).use { reader ->
        N = reader.readLine().toInt()
        for (i in 1..N) {
            val line = reader.readLine().split(" ").map { it.toInt() }
            arr[i] = line[0] to line[1]
        }
    }
}

fun recur(idx: Int) {
    if (idx > N) {
        return
    }
    for (i in idx - 1 downTo 1) {
        val arg = i + arr[i].first - 1
        if (arg >= idx) {
            continue
        }
        dp[idx] = max(dp[idx], dp[i])
    }
    dp[idx] += arr[idx].second
    if (idx + arr[idx].first - 1 > N) {
        dp[idx] -= arr[idx].second
    }
    ans = max(dp[idx], ans)
    recur(idx + 1)
}
