import java.io.BufferedReader
import java.io.InputStreamReader

private const val MIN_WEIGHT = 500
private const val MID_WEIGHT = 15500
private const val MAX_WEIGHT = 31_000 - MIN_WEIGHT
private var N = 0
private lateinit var weights: List<Int>
private var M = 0
private lateinit var beads: List<Int>
private val dp = Array(30) { Array(80_000) { false } }

fun main() {
    BufferedReader(InputStreamReader(System.`in`))
        .use {
            N = it.readLine().toInt()
            weights = it.readLine().split(" ").map { it.toInt() }
            M = it.readLine().toInt()
            beads = it.readLine().split(" ").map { it.toInt() }
        }
    setAllPossibleWeights()
    for (bead in beads) {
        when (dp[N-1][MID_WEIGHT + bead]) {
            true -> print("Y ")
            false -> print("N ")
        }
    }
}

private fun setAllPossibleWeights() {
    dp[0][MID_WEIGHT - weights.first()] = true
    dp[0][MID_WEIGHT] = true
    dp[0][MID_WEIGHT + weights.first()] = true
    for (weight in weights.withIndex()) {
        if (weight.index == 0) {
            continue
        }
        for (j in MIN_WEIGHT..MAX_WEIGHT) {
            dp[weight.index][j] = dp[weight.index - 1][j - weight.value]
                    || dp[weight.index - 1][j]
                    || dp[weight.index - 1][j + weight.value]
        }
    }
}
