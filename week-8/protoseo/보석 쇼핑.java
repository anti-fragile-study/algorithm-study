import java.util.*;

class Solution {
    Map<String, Integer> gemByCount = new HashMap<>();

    public int[] solution(String[] gems) {
        int[] answer = {-100001, 100001};
        int gemTypeCount = countGemType(gems);
        int l = 0;
        int r = 0;
        while (l <= r && r < gems.length) {
            if (gemByCount.size() < gemTypeCount) {
                String gem = gems[r++];
                gemByCount.put(gem, gemByCount.getOrDefault(gem, 0) + 1);
            } else if (gemByCount.size() == gemTypeCount) {
                if (answer[1] - answer[0] > r - l - 1) {
                    answer[0] = l + 1;
                    answer[1] = r;
                }
                String gem = gems[l++];
                int count = gemByCount.get(gem);
                if (count == 1) {
                    gemByCount.remove(gem);
                } else {
                    gemByCount.put(gem, count - 1);
                }
            }
        }
        while (l <= r && (gemByCount.size() == gemTypeCount)) {
            if (answer[1] - answer[0] > r - l - 1) {
                answer[0] = l + 1;
                answer[1] = r;
            }
            String gem = gems[l++];
            int count = gemByCount.get(gem);
            if (count == 1) {
                gemByCount.remove(gem);
            } else {
                gemByCount.put(gem, count - 1);
            }
        }
        return answer;
    }

    private int countGemType(String[] gems) {
        Set<String> s = new HashSet<>();
        for (String gem : gems) {
            s.add(gem);
        }
        return s.size();
    }
}
