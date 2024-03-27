import java.util.*;

class Solution {
    
    private static final int[] dr = {0, -1, 0, +1};
    private static final int[] dc = {-1, 0, +1, 0};
    
    private static int answer = 0;
    
    public int solution(final int[][] board, int[] A, int[] B) {
        final int[] result = play(0, board, A, B);
        
        return result[1];
    }
    
    private int[] play(final int round, final int[][] board, int[] A, int[] B) {
        // player 기준 최선의 선택을 반환
        // {게임 결과, 최선의 round}
        final int[] player = (round % 2 == 0) ? A : B;
        
        if(board[player[0]][player[1]] == 0) {
            return new int[] {0, round};
        }
        
        final List<int[]> results = new ArrayList<>();
        
        for(int dir = 0; dir < 4; dir++) {
            final int r = player[0] + dr[dir];
            final int c = player[1] + dc[dir];
            if(r < 0 || c < 0 || r >= board.length || c >= board[0].length) continue;
            if(board[r][c] == 0) continue;
            
            board[player[0]][player[1]] = 0;
            final int[] result = (round % 2 == 0) 
                ? play(round + 1, board, new int[] {r, c}, B) 
                : play(round + 1, board, A, new int[] {r, c});
            
            results.add(result);
            board[player[0]][player[1]] = 1;
        }
        
        if(results.size() == 0) return new int[] {0, round};
        
        int win = Integer.MAX_VALUE, lose = 0;
        for(final int[] result : results) {
            if(result[0] == 0) { // 상대방 lose
                win = Math.min(win, result[1]);
            }
            else { // 상대방 win
                lose = Math.max(lose, result[1]);
            }
        }
        return (win == Integer.MAX_VALUE) ? new int[] {0, lose} : new int[] {1, win};
    }
}
