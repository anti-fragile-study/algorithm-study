import java.util.*;

class Solution {
    
    private static int answer = 0;
        
    public int solution(final int N) {
        final boolean[][] board = new boolean[N][N];
        play(board, 0);
        return answer;
    }
    
    private void play(final boolean[][] board, final int r) {
        if(r == board.length) {
            answer++; 
            return;
        }
        
        for(int c = 0; c < board[r].length; c++) {
            if(check(board, r, c)) {
                board[r][c] = true;
                play(board, r + 1);
                board[r][c] = false;
            }
        }
    }
    
    private boolean check(final boolean[][] board, final int r, final int c) {
        // board[r][c] 가 true 가 될 수 있는지 검사
        // 1. 세로 검사
        for(int rr = 0; rr < r; rr++) {
            if(board[rr][c]) return false;
        }
        // 2. 대각선 검사
        // 2-1. \ 대각선
        for(int rr = r, cc = c; rr >= 0 && cc >= 0; rr--, cc--) {
            if(board[rr][cc]) return false;
        }
        // 2-2. / 대각선
        for(int rr = r, cc = c; rr >= 0 && cc < board[rr].length; rr--, cc++) {
            if(board[rr][cc]) return false;
        }
        return true;
    }
}
