import java.util.*;

class Solution {
    
    static class Point {
        int r, c;
        int dir;
        
        Point(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }
    
    private static final int[] dr = {0, +1, 0, -1};
    private static final int[] dc = {+1, 0, -1, 0};
    
    public int solution(final int[][] board) {
        for(int r = 0; r < board.length; r++) {
            for(int c = 0; c < board[r].length; c++) {
                System.out.printf("%2d ", board[r][c]);
            }
            System.out.println();
        }
        final int[][] costs = new int[board.length][board.length];
        for(int[] cost : costs) {
            Arrays.fill(cost, Integer.MAX_VALUE);
        }
        costs[0][0] = 0;
                
        final Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(0, 0, -1));
        
        while(!queue.isEmpty()) {
            final Point curr = queue.poll();
            
            if(curr.r == board.length - 1 && curr.c == board.length - 1) {
                continue;
            }
            
            for(int dir = 0; dir < 4; dir++) {
                final int r = curr.r + dr[dir];
                final int c = curr.c + dc[dir];
                if(r < 0 || c < 0 || r >= board.length || c >= board.length) continue;
                if(board[r][c] == 1) continue;
                
                final int cost = (curr.dir < 0 || curr.dir == dir) ? 100 : 600;
                if(costs[r][c] >= costs[curr.r][curr.c] + cost) {
                    costs[r][c] = costs[curr.r][curr.c] + cost;
                    queue.add(new Point(r, c, dir));
                }
            }
        }
        
        return costs[board.length - 1][board.length - 1];
    }
}
