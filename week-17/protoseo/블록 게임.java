import java.util.*;

class Solution {
    int[][] copiedBoard;
    int n;
    
    public int solution(int[][] board) {
        copiedBoard = board;
        n = board.length;
        int answer = 0;
        int result = check(copiedBoard);
        while (result > 0) {
            answer += result;
            result = check(copiedBoard);
        }
        return answer;
    }
    
    private int check(int[][] target) {
        int[][] filled = fill(target);
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i + 1 < n && j + 2 < n) {
                    result += checkVertical(filled, j, i, j + 2, i + 1);
                }
                if (i + 2 < n && j + 1 < n) {
                    result += checkVertical(filled, j, i, j + 1, i + 2);
                }
            }
        }
        return result;
    }
    
    private int checkVertical(int[][] filled, int x1, int y1, int x2, int y2) {
        Map<Integer, Integer> colors = new HashMap<>();
        for (int i = y1; i <= y2; i++) {
            for (int j = x1; j <= x2; j++) {
                int color = filled[i][j];
                colors.put(color, colors.getOrDefault(color, 0) + 1);
            }
        }
        if (colors.size() != 2) {
            return 0;
        } 
        for (int key : colors.keySet()) {
            if (key == -1 && colors.get(key) == 2) {
                continue;
            } 
            if (key != -1 && key != 0 && colors.get(key) == 4) {
                continue;
            }    
            return 0;
        }
        clear(x1, y1, x2, y2);
        return 1;
    }
    
    private void clear(int x1, int y1, int x2, int y2) {
        for (int i = y1; i <= y2; i++) {
            for (int j = x1; j <= x2; j++) {
                copiedBoard[i][j] = 0;
            }
        } 
    }
    
    private int[][] fill(int[][] target) {
        int[][] result = copy(target);
        
        for (int i = 0; i < n; i++) {
            int idx = n - 1;
            for (int j = n - 1; j >= 0; j--) {
                if (result[j][i] > 0) {
                    idx = j - 1;
                }
            }
            for (int j = idx; j >= 0; j--) {
                result[j][i] = -1;
            }
        }
        return result;
    }
    
    private int[][] copy(int[][] target) {
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = target[i][j];
            }
        }
        return result;
    }
}