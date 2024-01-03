import java.util.*;

class Solution {
    
    public int solution(final int N, int[][] edge) {
        final List<Integer>[] edges = new ArrayList[N + 1];
        for(int i = 0; i < edges.length; i++) {
            edges[i] = new ArrayList<>();
        }
        
        for(int[] e : edge) {
            edges[e[0]].add(e[1]);
            edges[e[1]].add(e[0]);
        }
        
        final int[] dists = new int[N + 1];
        Arrays.fill(dists, Integer.MAX_VALUE);
        dists[1] = 0;
        
        final Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        
        while(!queue.isEmpty()) {
            final int curr = queue.poll();
            
            for(int next : edges[curr]) {
                if(dists[next] > dists[curr] + 1) {
                    dists[next] = dists[curr] + 1;
                    queue.add(next);
                }
            }
        }
        
        int maxDist = 0;
        int answer = 0;
        for(int num = 2; num <= N; num++) {
            if(maxDist < dists[num]) {
                maxDist = dists[num];
                answer = 1;
            } else if(maxDist == dists[num]) {
                answer++;
            }
        }
        return answer;
    }
}
