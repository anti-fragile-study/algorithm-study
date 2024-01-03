import java.util.*;

class Solution {
    public int solution(final int[] stones, final int K) {
        int answer = 0;
        int left = 0, right = 200_000_000;
        
        while(left <= right) {
            final int mid = (int) ((left + (long) right) / 2);
            
            // 징검다리를 건널 수 있는지 검사
            int k = 0;
            boolean canArrive = true;
            for(int i = 0; i < stones.length; i++) {
                final int stone = stones[i] - mid;
                if(stone <= 0) {
                    k += 1;
                    if(k >= K) {
                        canArrive = false;
                        break;
                    }
                } else {
                    k = 0;
                }
            }

            if(canArrive) {
                left = mid + 1;
                answer = Math.max(answer, mid);
            } else{
                right = mid - 1;
            }
        }
        
        return answer + 1;
    }
}
