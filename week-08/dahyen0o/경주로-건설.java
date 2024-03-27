import java.util.*;

class Solution {
    
    private static final int[] dr = {-1, 0, +1, 0};
    private static final int[] dc = {0, +1, 0, -1};
    private static final int MAX = Integer.MAX_VALUE;
    
    private int N;
    private int[][] board;
    private int[][][] minCosts;
    
    public int solution(final int[][] board) {
        this.board = board;
        this.N = board.length;
        this.minCosts = new int[N][N][2];
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++)
                Arrays.fill(minCosts[r][c], MAX);
        }
        
        return minCost();
    }
    
    private int minCost() {
        Arrays.fill(minCosts[0][0], 0);
        
        final Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {0, 0, -1, 0}); // (r, c, dir, cost)
        
        while(!queue.isEmpty()) {
            final int[] curr = queue.poll();
            
            for(int dir = 0; dir < 4; dir++) {
                final int r = curr[0] + dr[dir];
                final int c = curr[1] + dc[dir];
                if(!inBound(r, c) || board[r][c] == 1) continue;
                
                final int cost = curr[3] + 100 + (isEdge(curr[2], dir) ? 500 : 0);
                if(minCosts[r][c][dir % 2] > cost) {
                    minCosts[r][c][dir % 2] = cost;
                    queue.add(new int[] {r, c, dir, cost});
                }
            }
        }
        
        return Math.min(minCosts[N - 1][N - 1][0], minCosts[N - 1][N - 1][1]);
    }
    
    private boolean inBound(final int r, final int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
    
    private boolean isEdge(final int d1, final int d2) {
        if(d1 < 0 || d2 < 0) return false;
        return ((d1 ^ d2) & 1) > 0;
    }
}
