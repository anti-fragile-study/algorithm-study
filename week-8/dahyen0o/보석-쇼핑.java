import java.util.*;

class Solution {
    public int[] solution(final String[] gems) {
        final Map<String, Integer> counts = new HashMap<>();
        for(String gem : gems) {
            counts.putIfAbsent(gem, 0);
        }

        if(counts.size() == 1) {
            return new int[] {1, 1};
        }

        final Set<String> contains = new HashSet<>();

        final int[] answer = {0, Integer.MAX_VALUE};
        int left = 0, right = 0;
        counts.compute(gems[0], (key, value) -> value + 1);
        contains.add(gems[0]);

        while(right < gems.length - 1) {
            if(contains.size() == counts.size() && answer[1] - answer[0] > right - left) {
                answer[0] = left;
                answer[1] = right;
            }

            if(contains.size() == counts.size()) {
                counts.compute(gems[left], (key, value) -> value - 1);
                if(counts.get(gems[left]) == 0) {
                    contains.remove(gems[left]);
                }
                left++;
            } else {
                right++;
                counts.compute(gems[right], (key, value) -> value + 1);
                contains.add(gems[right]);
            }
        }

        while(left < right) {
            if(contains.size() == counts.size() && answer[1] - answer[0] > right - left) {
                answer[0] = left;
                answer[1] = right;
            }

            counts.compute(gems[left], (key, value) -> value - 1);
            if(counts.get(gems[left]) == 0) {
                contains.remove(gems[left]);
            }
            left++;
        }

        answer[0]++;
        answer[1]++;
        return answer;
    }
}
