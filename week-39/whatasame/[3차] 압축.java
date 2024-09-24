// 무손실 알고리즘
// 입력은 모두 대문자

import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(String msg) {
        Map<String, Integer> dict = new HashMap<>();
        String[] capitals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
        for (int idx = 0; idx < capitals.length; idx++) {
            dict.put(capitals[idx], idx + 1);
        }

        int nxt = capitals.length + 1; // 27
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < msg.length(); i++) {
            String sub = null;
            for (int j = i; j < msg.length(); j++) {
                sub = msg.substring(i, j + 1);  // i to j

                if (!dict.containsKey(sub)) {
                    dict.put(sub, nxt++);
                    String prev = msg.substring(i, j); // i to j - 1 = sub - 1
                    answer.add(dict.get(prev));

                    i += prev.length() - 1;
                    sub = null;
                    break;
                }
            }
            if (sub != null) {
                answer.add(dict.get(sub));
                i += sub.length() - 1;
            }
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
