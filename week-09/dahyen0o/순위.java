import java.util.*;

class Solution {
    
    private static int MAX_DIST = Integer.MAX_VALUE / 2;
    
    public int solution(final int N, int[][] results) {        
        /* 플로이드-워셜 알고리즘 */
        final int[][] dists = new int[N + 1][N + 1];
        // 거리 초기화
        for(int i = 0; i < dists.length; i++) {
            Arrays.fill(dists[i], MAX_DIST);
            dists[i][i] = 0; // 출발지 == 목적지
        }
        for(int[] result : results) {
            dists[result[1]][result[0]] = 1; // 경기 결과 존재
        }
        
        for(int mid = 1; mid <= N; mid++) {
            for(int start = 1; start <= N; start++) {
                for(int end = 1; end <= N; end++) {
                    dists[start][end] = Math.min(dists[start][end], dists[start][mid] + dists[mid][end]);
                }
            }
        }
        
        /* 결과 */
        int answer = 0;
        
        for(int num = 1; num <= N; num++) {
            int count = 0;
            for(int start = 1; start <= N; start++) {
                if(dists[start][num] < MAX_DIST) {
                    count++;
                }
            }
            for(int end = 1; end <= N; end++) {
                if(dists[num][end] < MAX_DIST) {
                    count++;
                }
            }
            
            if(count == N + 1) { // 이긴 횟수 + 진 횟수 + 내 결과(1 * 2)
                answer++;
            }
        }
        
        return answer;
    }
}
