class Solution {

    val allMenus: MutableList<MutableList<Pair<Int, String>>> = MutableList(11) { mutableListOf() }

    fun solution(orders: Array<String>, course: IntArray): Array<String> {
        fillAllMenu(orders, course)
        val answer = mutableSetOf<String>()
        for (cours in course) {
            val menu = allMenus[cours]
            if (menu.isEmpty()) {
                continue
            }
            menu.sortWith { o1, o2 -> o2.first.compareTo(o1.first) }
            for (eachMenu in menu.withIndex()) {
                if (eachMenu.index > 0 && (menu[eachMenu.index - 1].first > eachMenu.value.first)) {
                    break
                }
                answer.add(eachMenu.value.second)
            }
        }
        return answer.sorted().toTypedArray()
    }

    private fun fillAllMenu(orders: Array<String>, course: IntArray) {
        for (cours in course) {
            for (order in orders) {
                val splited = order.split().sorted()
                val candidates = mutableListOf<String>()
                makeCandidateWords(0, splited, "", cours, candidates)
                for (candidate in candidates) {
                    val count = orderedCount(candidate, orders)
                    if (count <= 1) {
                        continue
                    }
                    allMenus[cours].add(count to candidate)
                }
            }
        }
    }

    private fun makeCandidateWords(
        idx: Int,
        words: List<String>,
        currentWord: String,
        maxCount: Int,
        candidates: MutableList<String>
    ) {
        if (currentWord.length == maxCount) {
            candidates.add(currentWord)
            return
        }
        for (i in idx until words.size) {
            makeCandidateWords(i + 1, words, currentWord + words[i], maxCount, candidates)
        }
    }

    private fun orderedCount(candidate: String, orders: Array<String>): Int {
        var count = 0
        val splited = candidate.split()
        for (order in orders) {
            var allContains = true
            for (split in splited) {
                if (!order.contains(split)) {
                    allContains = false
                    break
                }
            }
            if (allContains) {
                count++
            }
        }
        return count
    }

    private fun String.split(): List<String> {
        val splited = this.split("")
        return splited.subList(1, splited.size - 1)
    }
}
