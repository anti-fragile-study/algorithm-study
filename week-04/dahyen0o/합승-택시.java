import java.util.*;

class Solution {
    
    private static final long MAX = Integer.MAX_VALUE;
    private int[][] costs;
        
    public int solution(int n, int s, int a, int b, int[][] fares) {
        // 1. A, B, S로 다익스트라 구하기
        costs = new int[n + 1][n + 1];
        for(int r = 0; r < costs.length; r++) {
            Arrays.fill(costs[r], Integer.MAX_VALUE);
        }
        for(int[] fare : fares) {
            costs[fare[0]][fare[1]] = fare[2];
            costs[fare[1]][fare[0]] = fare[2];
        }
        
        final long[] aDist = dijkstra(a, n);
        final long[] bDist = dijkstra(b, n);
        final long[] sDist = dijkstra(s, n);
        
        // 2. S-X + X-A + X-B 가 최소인 X 구하기
        long minDist = 3 * MAX;

        for(int x = 1; x <= n; x++) {
            final long dist = sDist[x] + aDist[x] + bDist[x];
            minDist = Math.min(minDist, dist);
        }
        
        return (int) minDist;
    }
    
    private long[] dijkstra(final int start, final int N) {
        final long[] dists = new long[N + 1];
        Arrays.fill(dists, MAX);
        dists[start] = 0;
        
        final boolean[] visited = new boolean[N + 1];
        
        for(int count = 0; count < N; count++) {
            // 탐색을 시작할 지점 찾기
            int curr = 1;
            long minDist = MAX;
            
            for(int n = 1; n <= N; n++) {
                if(!visited[n] && minDist > dists[n]) {
                    curr = n;
                    minDist = dists[n];
                }
            }
            
            visited[curr] = true;
            
            for(int next = 1; next < costs[curr].length; next++) {
                if(!visited[next] && dists[curr] + costs[curr][next] < dists[next]) {
                    dists[next] = dists[curr] + costs[curr][next];
                }
            }
        }
        
        return dists;
    }
}
