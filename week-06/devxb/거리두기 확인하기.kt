class Solution {
    fun solution(places: Array<Array<String>>): IntArray {
        val answer = mutableListOf<Int>();
        for (place in places) {
            var isSuccess = true
            val check = Array(5) { IntArray(5) { 10 } }
            for (y in 0..4) {
                if (!isSuccess) {
                    break
                }
                for (x in 0..4) {
                    if (place[y][x] == 'P') {
                        val result = dfs(place, y, x, 0, check)
                        if (result > 0) {
                            isSuccess = false
                            break
                        }
                    }
                }
            }
            if (isSuccess) {
                answer.add(1)
                continue
            }
            answer.add(0)
        }
        return answer.toIntArray()
    }

    private fun dfs(
        fields: Array<String>,
        y: Int,
        x: Int,
        dist: Int,
        check: Array<IntArray>
    ): Int {
        if (y < 0 || x < 0 || y > 4 || x > 4 || fields[y][x] == 'X' || dist > 2 || check[y][x] < dist) {
            return 0
        }

        check[y][x] = dist

        if (fields[y][x] == 'P' && dist > 0) {
            return 1
        }

        var ans = dfs(fields, y + 1, x, dist + 1, check)
        ans += dfs(fields, y, x + 1, dist + 1, check)
        ans += dfs(fields, y - 1, x, dist + 1, check)
        ans += dfs(fields, y, x - 1, dist + 1, check)
        return ans
    }
}
