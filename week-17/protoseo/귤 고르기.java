import java.util.*;

class Solution {
    Map<Integer, Integer> tangerines = new HashMap<>();
    
    public int solution(int k, int[] tangerine) {
        for (int t : tangerine) {
            tangerines.put(t, tangerines.getOrDefault(t, 0) + 1);
        }
        List<int[]> tangerineList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : tangerines.entrySet()) {
            tangerineList.add(new int[]{ entry.getKey(), entry.getValue() });
        }
        Collections.sort(tangerineList, (o1, o2) -> o2[1] - o1[1]);
        
        int answer = 0;
        for (int[] t : tangerineList) {
            k -= t[1];
            answer++;
            if (k <= 0) {
                break;
            }
        }
        return answer;
    }
}