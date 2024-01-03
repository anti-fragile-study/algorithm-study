import java.util.Deque;
import java.util.ArrayDeque;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Deque<Integer> stk = new ArrayDeque<>();
        for (int x : moves) {
           	int result = pullDoll(x - 1, board); 
            if (result == 0) {
              	continue;  
            }
            
           	if (!stk.isEmpty() && stk.peekLast() == result) {
                answer+=2;
                stk.pollLast();
            } else {
                stk.addLast(result);
            }
            
        }
        return answer;
    }
    
    public int pullDoll(int x, int[][] board) {
        int y = 0;
        while (y < board.length) {
            int result = board[y][x];
            if (result != 0) {
                board[y][x] = 0;
                return result;
            }
            y++;
        }
        return 0;
    } 
}