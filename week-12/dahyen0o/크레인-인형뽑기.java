import java.util.*;

class Solution {
        
    public int solution(final int[][] board, final int[] moves) {
        final Stack<Integer> bag = new Stack<>();
        int answer = 0;
        
        for(int c : moves) {
            c--;
            // 인형 뽑기
            for(int r = 0; r < board.length; r++) {
                if(board[r][c] == 0) continue;
                // 바구니에 넣기
                final int doll = board[r][c];
                board[r][c] = 0;
                if(!bag.isEmpty() && bag.peek() == doll) {
                    bag.pop();
                    answer += 2;
                    break;
                }
                bag.push(doll);
                break;
            }
        }
        return answer;
    }
}
