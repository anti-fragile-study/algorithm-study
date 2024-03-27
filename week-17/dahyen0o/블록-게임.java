import java.util.*;

class Solution {
    
    private static final int[] dr = {0, -1, 0, +1};
    private static final int[] dc = {-1, 0, +1, 0};
    
    private static int N;
    private static int answer = 0;
    
    public int solution(final int[][] board) {
        N = board.length;
        
        boolean removed = false;
        // col을 돌면서 지울 수 있는 블록을 지운다.
        // 만약 지울 수 없는 블록이 없다면 탐색을 종료한다.
        do {
            removed = false;
            for(int col = 0; col < N; col++) {
                final int row = findHighestRow(board, col);
                if(row == N) continue;
                
                if(remove(board, row, col)) {
                    answer++;
                    removed = true;
                }
            }
        } while(removed);
        
        return answer;
    }
    
    private int findHighestRow(final int[][] board, final int col) {
        for(int r = 0; r < N; r++) {
            if(board[r][col] > 0) return r;
        }
        return N;
    }
    
    private boolean remove(final int[][] board, final int row, final int col) {
        /* 
            해당 블럭을 지울 수 있는 지 확인한다. 
            지울 수 있으면 지우고 true를 리턴하고
            지울 수 없으면 false를 반환한다.
        */
        
        final int type = board[row][col];
        
        // block 정보 탐색
        final List<Block> block = findBlock(board, row, col);
        int minR = N, minC = N, maxR = 0, maxC = 0;
        for(Block b : block) {
            minR = Math.min(minR, b.r);
            minC = Math.min(minC, b.c);
            maxR = Math.max(maxR, b.r);
            maxC = Math.max(maxC, b.c);
        }
        
        // col 마다 직사각형을 만들 수 있는 지 검사
        for(int c = minC; c <= maxC; c++) {
            boolean blocked = false; // 윗부분이 블록으로 막혀있는 지
            for(int r = 0; r < minR; r++) {
                if(board[r][c] > 0) {
                    blocked = true; break;
                }
            }
            for(int r = minR; r <= maxR; r++) {
                if(board[r][c] == 0 && blocked) return false;
                if(board[r][c] == 0) continue;
                if(board[r][c] != type) return false;
                if(board[r][c] > 0) blocked = true;
            }
        }
        
        // 블럭 지우기
        for(int r = minR; r <= maxR; r++) {
            for(int c = minC; c <= maxC; c++) {
                if(board[r][c] == type) board[r][c] = 0;
            }
        }
        
        return true;
    }
    
    private List<Block> findBlock(final int[][] board, final int row, final int col) {
        final List<Block> block = new ArrayList<>();
        final int type = board[row][col];
        
        final Queue<Block> queue = new ArrayDeque<>();
        queue.add(new Block(row, col));
        final boolean[][] visited = new boolean[N][N];
        visited[row][col] = true;
        
        while(!queue.isEmpty()) {
            final Block curr = queue.poll();
            block.add(curr);
            
            for(int dir = 0; dir < 4; dir++) {
                final int r = curr.r + dr[dir];
                final int c = curr.c + dc[dir];
                
                if(!inBound(r, c) || visited[r][c] || board[r][c] != type) continue;
                visited[r][c] = true;
                queue.add(new Block(r, c));
            }
        }
        
        return block;
    }
    
    private boolean inBound(final int r, final int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }

    static class Block {
        int r, c;
        
        Block(int r, int c) {
            this.r = r;
            this.c = c;
        }
        
        @Override
        public String toString() {
            return "(" + r + " " + c + ")";
        }
    }
}
