import java.util.*;

class Solution {
    
    List<int[]> firsts = new ArrayList<>();
    List<int[]> seconds = new ArrayList<>();
    
    public int solution(String[] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char c = board[i].charAt(j);
                if (c == 'O') {
                    firsts.add(new int[]{j, i});
                } else if (c == 'X') {
                    seconds.add(new int[]{j, i});
                }
            }
        }
        if (firsts.size() < seconds.size() || firsts.size() > seconds.size() + 1) {
            return 0;
        }
        
        boolean firstWin = isWin(board, 'O');
        boolean secondWin = isWin(board, 'X');
        if (firstWin && secondWin) {
            return 0;
        }
		if (secondWin && firsts.size() > seconds.size()) {
            return 0;
        }
        if (firstWin && firsts.size() <= seconds.size()) {
            return 0;
        }
        return 1;
    }
    
    private boolean isWin(String[] board, char t) {
        boolean result = false;
        for (int i = 0; i < 3; i++) {
            result |= (board[i].charAt(0) == t && board[i].charAt(1) == t && board[i].charAt(2) == t);
            result |= (board[0].charAt(i) == t && board[1].charAt(i) == t && board[2].charAt(i) == t);
        }
        result |= (board[0].charAt(0) == t && board[1].charAt(1) == t && board[2].charAt(2) == t);
        result |= (board[0].charAt(2) == t && board[1].charAt(1) == t && board[0].charAt(2) == t);
        return result;
    }
}