import java.util.*;

class Solution {
    public int solution(final int DIST, int[] rocks, final int N) {
        Arrays.sort(rocks);
        
        final int[] dists = new int[rocks.length + 1];
        dists[0] = rocks[0];
        dists[dists.length - 1] = DIST - rocks[rocks.length - 1];
        for(int i = 0; i < rocks.length - 1; i++) {
            dists[i + 1] = rocks[i + 1] - rocks[i];
        }
        
        int answer = 0;
        int left = 0, right = DIST;
        
        while(left <= right) {
            int mid = (left + right) / 2;
            if(isUpperBound(mid, dists, N)) {
                answer = Math.max(answer, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return answer;
    }
    
    private boolean isUpperBound(final int min, int[] oldDists, final int N) {
        int n = 0;
        final int[] dists = oldDists.clone();
        
        for(int i = 0; i < dists.length - 1; i++) {
            if(dists[i] >= min) continue;
            if(n < N) {
                // 다음 거리와 합칠 수 있음 (바위 제거)
                n++;
                dists[i + 1] += dists[i];
                continue;
            } 
            return false;
        }
        
        // 마지막 거리 탐색
        final int i = dists.length - 1;
        if(dists[i] >= min) return true;
        if(n >= N) return false;
        // 이전 거리와 합칠 수 있음
        // (이전 거리는 무조건 min 이상으로 조건 충족)
        return true;
    }
}
