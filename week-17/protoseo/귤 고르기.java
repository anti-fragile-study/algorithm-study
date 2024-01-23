import java.util.*;

class Solution {
    Map<Integer, Integer> tangerines = new HashMap<>();
    
    public int solution(int k, int[] tangerine) {
        for (int t : tangerine) {
            tangerines.put(t, tangerines.getOrDefault(t, 0) + 1);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return o1[1] - o2[1];
        });
        for (Map.Entry<Integer, Integer> entry : tangerines.entrySet()) {
            pq.add(new int[]{ entry.getKey(), entry.getValue() });
        }
        for (int i = k; i < tangerine.length; i++) {
            int[] p = pq.poll();
            if (p[1] > 1) {
                pq.add(new int[]{ p[0], p[1] - 1 });
            }
        }
        return pq.size();
    }
}