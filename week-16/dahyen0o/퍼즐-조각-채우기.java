import java.util.*;

class Solution {
    
    static class Point {
        int r, c;
        
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    private static final int[] dr = {-1, 0, +1, 0};
    private static final int[] dc = {0, -1, 0, +1};
    
    private static final List<boolean[][]> blanks = new ArrayList<>();
    private static final List<boolean[][]> puzzles = new ArrayList<>();
    
    public int solution(int[][] board, int[][] table) {
        initBlanks(board);
        initPuzzles(table);
        
        int answer = 0;
        
        final boolean[] matched = new boolean[puzzles.size()];
        for(boolean[][] blank : blanks) {
            for(int p = 0; p < puzzles.size(); p++) {
                if(matched[p]) continue;
                final int result = matches(blank, puzzles.get(p));
                if(result > 0) {
                    matched[p] = true;
                    answer += result;
                }
            }
        }
        
        return answer;
    }
    
    private int matches(final boolean[][] blank, final boolean[][] puzzle) {
        for(int br = 0; b)
        return 0;
    }
    
    private void initBlanks(final int[][] board) {
        for(int r = 0; r < board.length; r++) {
            for(int c = 0; c < board[r].length; c++) {
                if(board[r][c] == 0) {
                    addBlank(board, r, c);
                }
            }
        }
    }
    
    private void addBlank(final int[][] board, 
                          final int startR, 
                          final int startC) {
        // startR, startC가 (0, 0)인 blank
        final boolean[][] blank = new boolean[board.length - startR][board.length];
        blank[0][startC] = true;
        board[startR][startC] = 1;
        
        final Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(startR, startC));
        
        while(!queue.isEmpty()) {
            final Point curr = queue.poll();
            
            for(int dir = 0; dir < 4; dir++) {
                final int r = curr.r + dr[dir];
                final int c = curr.c + dc[dir];
                
                if(!inBound(r, c, board) || board[r][c] == 1) continue;
                
                blank[r - startR][c] = true;
                board[r][c] = 1;
                queue.add(new Point(r, c));
            }
        }
        
        blanks.add(blank);
    }
    
    private void initPuzzles(final int[][] board) {
        for(int r = 0; r < board.length; r++) {
            for(int c = 0; c < board[r].length; c++) {
                if(board[r][c] == 1) {
                    addPuzzle(board, r, c);
                }
            }
        }
    }
    
    private void addPuzzle(final int[][] board, 
                           final int startR, 
                           final int startC) {
        // startR, startC가 (0, 0)인 puzzle
        final boolean[][] puzzle = new boolean[board.length - startR][board.length];
        puzzle[0][startC] = true;
        board[startR][startC] = 0;
        
        final Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(startR, startC));
        
        while(!queue.isEmpty()) {
            final Point curr = queue.poll();
            
            for(int dir = 0; dir < 4; dir++) {
                final int r = curr.r + dr[dir];
                final int c = curr.c + dc[dir];
                
                if(!inBound(r, c, board) || board[r][c] == 0) continue;
                
                puzzle[r - startR][c] = true;
                board[r][c] = 0;
                queue.add(new Point(r, c));
            }
        }
        
        puzzles.add(puzzle);
    }
    
    private boolean inBound(final int r, final int c, final int[][] arr) {
        return r >= 0 && c >= 0 && r < arr.length && c < arr[0].length;
    }
}
