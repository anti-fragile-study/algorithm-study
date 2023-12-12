class Solution {
    fun solution(info: Array<String>, queries: Array<String>): IntArray {
        val answer = mutableListOf<Int>()

        val applicants = mutableMapOf<Applicant, MutableList<Int>>()

        info.forEach {
            val applicantInfo = it.split(" ")
            val applicant = Applicant(
                applicantInfo[0],
                applicantInfo[1],
                applicantInfo[2],
                applicantInfo[3],
            )
            applicants.putIfAbsent(applicant, mutableListOf())
            applicants[applicant]!!.add(applicantInfo[4].toInt())
        }

        applicants.forEach { it.value.sort() }

        queries.forEach { query ->
            var count = 0
            for (applicant in applicants) {
                if (applicant.key.queryMatched(query)) {
                    val score = query.split(" and ")[3].split(" ")[1].toInt()
                    val scoreIdx = lowerBound(applicant.value, score)
                    if (scoreIdx >= 0) {
                        count += applicant.value.size - scoreIdx
                    }
                }
            }
            answer.add(count)
        }
        return answer.toIntArray()
    }

    fun lowerBound(arr: List<Int>, target: Int): Int {
        var left = 0
        var right = arr.size
        while (left < right) {
            val mid = (left + right) / 2
            if (arr[mid] < target) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        return right
    }

    class Applicant(
        private val language: String,
        private val position: String,
        private val career: String,
        private val soulFood: String,
    ) {

        fun queryMatched(query: String): Boolean {
            val conditions = query.split(" and ")
            val language = conditions[0]
            val position = conditions[1]
            val career = conditions[2]
            val soulFoodAndScore = conditions[3].split(" ")
            val soulFood = soulFoodAndScore[0]

            return (language == this.language || language == IGNORE)
                    && (position == this.position || position == IGNORE)
                    && (career == this.career || career == IGNORE)
                    && (soulFood == this.soulFood || soulFood == IGNORE)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Applicant) return false

            if (language != other.language) return false
            if (position != other.position) return false
            if (career != other.career) return false
            return soulFood == other.soulFood
        }

        override fun hashCode(): Int {
            var result = language.hashCode()
            result = 31 * result + position.hashCode()
            result = 31 * result + career.hashCode()
            result = 31 * result + soulFood.hashCode()
            return result
        }
    }

    companion object {
        private const val IGNORE = "-"
    }
}
