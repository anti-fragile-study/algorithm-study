import java.util.*;

class Solution {
    
    private static final Map<Integer, Integer> countBySize = new HashMap<>();
    
    public int solution(final int K, final int[] tangerines) {
        // 귤 크기 별 개수 저장
        for(int tangerine : tangerines) {
            final int val = countBySize.getOrDefault(tangerine, 0);
            countBySize.put(tangerine, val + 1);
        }
        
        // 귤 크기 기준으로 내림차순 정렬
        final List<Integer> counts = new ArrayList<>(countBySize.values());
        Collections.sort(counts, Comparator.reverseOrder());
        
        // 담을 귤 탐색
        int sum = 0;
        for(int answer = 0; answer < counts.size(); answer++) {
            sum += counts.get(answer);
            if(sum >= K) return answer + 1;
        }
        return counts.size();
    }
}
