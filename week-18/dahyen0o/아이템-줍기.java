import java.util.*;

class Solution {
    
    private static final int[] dr = {0, -1, 0, +1};
    private static final int[] dc = {-1, 0, +1, 0};
    private static final int N = 50;
    private static final boolean[][] isBorder = new boolean[N + 1][N + 1];
    
    public int solution(int[][] rectangles, int X, int Y, int itemX, int itemY) {
        setBorder(rectangles, X, Y);
        // print(isBorder);
        // return 0;
        return minDist(rectangles, X, Y, itemX, itemY);
    }
    
    private void print(boolean[][] arr) {
        for(int r = 1; r < 10; r++) {
            for(int c = 1; c < 10; c++) {
                if(arr[r][c]) System.out.printf("T ");
                else System.out.printf("  ");
            }
            System.out.println();
        }
    }
    
    private int minDist(final int[][] rectangles, 
                        final int startR, final int startC, 
                        final int endR, final int endC) {
        final Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(startR, startC, 0));
        
        final boolean[][] visited = new boolean[N + 1][N + 1];
        visited[startR][startC] = true;
        
        while(!queue.isEmpty()) {
            final Point curr = queue.poll();
            
            if(curr.r == endR && curr.c == endC) {
                return curr.dist;
            }
            
            for(int dir = 0; dir < 4; dir++) {
                final int r = curr.r + dr[dir];
                final int c = curr.c + dc[dir];
                if(!inBound(r, c) || visited[r][c] || !isBorder[r][c]) continue;
                
                visited[r][c] = true;
                queue.add(new Point(r, c, curr.dist + 1));
            }
        }
        throw new RuntimeException("NO PATH");
    }
    
    private void setBorder(final int[][] rectangles, final int R, final int C) {
        isBorder[R][C] = true; 
        
        final Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(R, C));
        System.out.println(queue.peek());
        
        final boolean[][] visited = new boolean[N + 1][N + 1];
        visited[R][C] = true;
        
        while(!queue.isEmpty()) {
            final Point curr = queue.poll();
            
            boolean isBorder = false;
            for(int dir = 0; dir < 4; dir++) {
                final int r = curr.r + dr[dir];
                final int c = curr.c + dc[dir];
                if(!inBound(r, c) || visited[r][c]) continue;
                if(!inRectangles(rectangles, r, c)) {
                    isBorder = true; continue;
                }
                
                visited[r][c] = true;
                queue.add(new Point(r, c));
            }
            
            if(isEdge(rectangles, curr.r, curr.c)) isBorder = true;
            if(isBorder) this.isBorder[curr.r][curr.c] = true;    
            // if(isBorder) System.out.println(curr);
        }
    }
    
    private boolean isEdge(final int[][] rectangles, final int r, final int c) {
        int result = 0;
        for(final int[] rec : rectangles) {
            if(rec[0] == r && rec[1] <= c && rec[3] >= c) result++;
            if(rec[2] == r && rec[1] <= c && rec[3] >= c) result++;
            if(rec[1] == c && rec[0] <= r && rec[2] >= r) result++;
            if(rec[3] == c && rec[0] <= r && rec[2] >= r) result++;
        }
        if(result >= 2) {
            // System.out.printf("edge %d %d%n", r, c);
        }
        return result >= 2;
    }
    
    private boolean inBound(final int r, final int c) {
        return r >= 0 && c >= 0 && r <= N && c <= N;
    } 
    
    private boolean inRectangles(final int[][] rectangles, final int R, final int C) {
        for(final int[] rec : rectangles) {
            if(rec[1] <= C && rec[3] >= C && rec[0] <= R && rec[2] >= R) 
                return true;
        }
        return false;
    }
    
    static class Point {
        
        final int r, c;
        final int dist;
        
        Point(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
        
        Point(int r, int c) {
            this.r = r;
            this.c = c;
            this.dist = 0;
        }
        
        @Override
        public String toString() {
            return String.format("[%d, %d]", r, c);
        }
    }
}
