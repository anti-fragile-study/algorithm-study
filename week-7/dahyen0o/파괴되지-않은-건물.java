import java.util.*;

class Solution {
    public int solution(final int[][] board, final int[][] skills) {
        final int[][] bboard = new int[board.length][board[0].length];
        
        for(int[] skill : skills) {
            final int r1 = skill[1];
            final int c1 = skill[2];
            final int r2 = skill[3];
            final int c2 = skill[4];
            final int degree = skill[0] == 1 ? -skill[5] : skill[5];
            
            bboard[r1][c1] += degree;
            if(r2 + 1 < board.length) {
                bboard[r2 + 1][c1] -= degree;
            }
            if(c2 + 1 < board[r1].length) {
                bboard[r1][c2 + 1] -= degree;
            }
            if(r2 + 1 < board.length && c2 + 1 < board[r2].length) {
                bboard[r2 + 1][c2 + 1] += degree;
            }
            // print(bboard);
            // System.out.println();
        }
                
        int answer = 0;
        
        for(int r = 0; r < board.length; r++) {
            for(int c = 0; c < board[r].length; c++) {
                if(c > 0) bboard[r][c] += bboard[r][c - 1];
            }
        }
        for(int c = 0; c < board[0].length; c++) {
            for(int r = 0; r < board.length; r++) {
                if(r > 0) bboard[r][c] += bboard[r - 1][c];
            }
        }
        
        for(int r = 0; r < board.length; r++) {
            for(int c = 0; c < board[r].length; c++) {
                if(board[r][c] + bboard[r][c] > 0) {
                    answer++;
                }
            }
        }
        // print(board);
        
        return answer;
    }
    
    private void print(final int[][] board) {
        for(int r = 0; r < board.length; r++) {
            for(int c = 0; c < board[r].length; c++) {
                System.out.printf("%3d ", board[r][c]);
            }
            System.out.println();
        }
    }
}
