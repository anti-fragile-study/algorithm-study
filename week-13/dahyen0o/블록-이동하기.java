import java.util.*;

class Solution {
    
    static class Robot {
        int r1, c1, r2, c2;
        int depth;
        
        Robot(int r1, int c1, int r2, int c2, int depth) {
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
            this.depth = depth;
        }
    }
    
    private static final int[] dr = {-1, 0, +1, 0};
    private static final int[] dc = {0, -1, 0, +1};
    private static int N;
    
    public int solution(final int[][] board) {
        N = board.length;
        
        // BFS
        final boolean[][][][] visited = new boolean[N][N][N][N];
        visited[0][0][0][1] = true;
        visited[0][1][0][0] = true;
        
        final Queue<Robot> queue = new ArrayDeque<>();
        queue.add(new Robot(0, 0, 0, 1, 0));
        
        while(!queue.isEmpty()) {
            final Robot curr = queue.poll();
            
            if(arrived(curr.r1, curr.c1) || arrived(curr.r2, curr.c2)) {
                return curr.depth;
            }
            
            // 이동
            for(int dir = 0; dir < 4; dir++) {
                final int r1 = curr.r1 + dr[dir];
                final int c1 = curr.c1 + dc[dir];
                final int r2 = curr.r2 + dr[dir];
                final int c2 = curr.c2 + dc[dir];
                
                if(!inBound(r1, c1) || !inBound(r2, c2)) continue;
                if(visited[r1][c1][r2][c2]) continue;
                if(visited[r2][c2][r1][c1]) continue;
                if(board[r1][c1] > 0 || board[r2][c2] > 0) continue;
                
                visited[r1][c1][r2][c2] = true;
                visited[r2][c2][r1][c1] = true;
                queue.add(new Robot(r1, c1, r2, c2, curr.depth + 1));
            }
            
            // 회전
            // 1. 세로 차 회전
            if(curr.c1 == curr.c2) {
                // 차 내부 정렬 (r1이 위로 가도록)
                if(curr.r1 > curr.r2) {
                    int temp = curr.r1;
                    curr.r1 = curr.r2;
                    curr.r2 = temp;
                }
                
                // 윗부분 회전 (r1, c1)
                for(int dir = 0; dir < 2; dir++) {
                    // 중간 지점
                    int r1 = curr.r1 + dr[dir * 2 + 1];
                    int c1 = curr.c1 + dc[dir * 2 + 1];
                    
                    if(!inBound(r1, c1)) continue;
                    if(board[r1][c1] > 0) continue;
                    
                    // 도착 지점
                    r1 += dr[2];
                    c1 += dc[2];
                    
                    if(!inBound(r1, c1)) continue;
                    if(board[r1][c1] > 0) continue;
                    if(visited[r1][c1][curr.r2][curr.c2]) continue;
                    if(visited[curr.r2][curr.c2][r1][c1]) continue;
                    
                    visited[r1][c1][curr.r2][curr.c2] = true;
                    visited[curr.r2][curr.c2][r1][c1] = true;
                    queue.add(new Robot(r1, c1, curr.r2, curr.c2, curr.depth + 1));
                }
                
                // 아랫부분 회전 (r2, c2)
                for(int dir = 0; dir < 2; dir++) {
                    // 중간 지점
                    int r2 = curr.r2 + dr[dir * 2 + 1];
                    int c2 = curr.c2 + dc[dir * 2 + 1];
                    
                    if(!inBound(r2, c2)) continue;
                    if(board[r2][c2] > 0) continue;
                    
                    // 도착 지점
                    r2 += dr[0];
                    c2 += dc[0];
                    
                    if(!inBound(r2, c2)) continue;
                    if(board[r2][c2] > 0) continue;
                    if(visited[curr.r1][curr.c1][r2][c2]) continue;
                    if(visited[r2][c2][curr.r1][curr.c1]) continue;
                    
                    visited[curr.r1][curr.c1][r2][c2] = true;
                    visited[r2][c2][curr.r1][curr.c1] = true;
                    queue.add(new Robot(curr.r1, curr.c1, r2, c2, curr.depth + 1));
                }
            }
            
            // 2. 가로 차 회전
            else {
                // 차 내부 정렬 (c1이 왼쪽으로 가도록)
                if(curr.c1 > curr.c2) {
                    int temp = curr.c1;
                    curr.c1 = curr.c2;
                    curr.c2 = temp;
                }
                
                // 왼쪽 부분 회전 (r1, c1)
                for(int dir = 0; dir < 2; dir++) {
                    // 중간 지점
                    int r1 = curr.r1 + dr[dir * 2];
                    int c1 = curr.c1 + dc[dir * 2];
                    
                    if(!inBound(r1, c1)) continue;
                    if(board[r1][c1] > 0) continue;
                    
                    // 도착 지점
                    r1 += dr[3];
                    c1 += dc[3];
                    
                    if(!inBound(r1, c1)) continue;
                    if(board[r1][c1] > 0) continue;
                    if(visited[r1][c1][curr.r2][curr.c2]) continue;
                    if(visited[curr.r2][curr.c2][r1][c1]) continue;
                    
                    visited[r1][c1][curr.r2][curr.c2] = true;
                    visited[curr.r2][curr.c2][r1][c1] = true;
                    queue.add(new Robot(r1, c1, curr.r2, curr.c2, curr.depth + 1));
                }
                
                // 오른쪽 부분 회전 (r2, c2)
                for(int dir = 0; dir < 2; dir++) {
                    // 중간 지점
                    int r2 = curr.r2 + dr[dir * 2];
                    int c2 = curr.c2 + dc[dir * 2];
                    
                    if(!inBound(r2, c2)) continue;
                    if(board[r2][c2] > 0) continue;
                    
                    // 도착 지점
                    r2 += dr[1];
                    c2 += dc[1];
                    
                    if(!inBound(r2, c2)) continue;
                    if(board[r2][c2] > 0) continue;
                    if(visited[curr.r1][curr.c1][r2][c2]) continue;
                    if(visited[r2][c2][curr.r1][curr.c1]) continue;
                    
                    visited[curr.r1][curr.c1][r2][c2] = true;
                    visited[r2][c2][curr.r1][curr.c1] = true;
                    queue.add(new Robot(curr.r1, curr.c1, r2, c2, curr.depth + 1));
                }
            }
        }
        
        throw new RuntimeException();
    }
    
    private boolean arrived(final int r, final int c) {
        return r == N - 1 && c == N - 1;
    }
    
    private boolean inBound(final int r, final int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}
