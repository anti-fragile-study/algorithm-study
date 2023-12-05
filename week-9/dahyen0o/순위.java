import java.util.*;

class Solution {
    
    // from[a] = b: a -> b
    private static List<Integer>[] froms;
    private static boolean[][] wVisited, lVisited;
    
    public int solution(final int N, int[][] results) {
        froms = new ArrayList[N + 1];
        init(froms);
        
        for(int[] result : results) {
            froms[result[1]].add(result[0]);
        }
        
        // 선수마다 이긴 선수들 몇 명인지 카운트
        final int[] wins = new int[N + 1];
        final int[] loses = new int[N + 1];
        wVisited = new boolean[N + 1][N + 1];
        lVisited = new boolean[N + 1][N + 1];
        for(int n = 1; n <= N; n++) {
            count(n, wins, loses);
        }
        
        System.out.println(Arrays.toString(wins));
        System.out.println(Arrays.toString(loses));
        
        // 순위를 매길 수 있는 지 판단
        int answer = 0;
        for(int n = 1; n <= N; n++) {
            if(wins[n] + loses[n] == N - 1) {
                answer++;
            }
        }
        
        return answer;
    }
    
    private void count(final int start, final int[] wins, final int[] loses) {
        final Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
                
        while(!queue.isEmpty()) {
            final int curr = queue.poll();
            
            for(int next : froms[curr]) {
                if(!wVisited[next][start]) {
                    wins[next]++;
                    wVisited[next][start] = true;
                }
                if(!lVisited[start][next]) {
                    loses[start]++;
                    lVisited[next] = true;
                }
                
                queue.add(next);
            }
        }
    }
    
    private void init(final List<Integer>[] list) {
        for(int i = 0; i < list.length; i++) {
            list[i] = new ArrayList<>();
        }
    }
}
