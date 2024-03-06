import java.util.*;

class Solution {
    
    static final int CARPET = 1; // 보
    static final int PILLAR = 0; // 기둥
    
    static int N;
    static boolean[][][][] board; // board[x1][y1][x2][y2]: 해당 선분에 설치
    
    public int[][] solution(final int N, final int[][] commands) {
        this.N = N;
        this.board = new boolean[N + 1][N + 1][N + 1][N + 1];
        
        for(final int[] command: commands) {
            if(command[3] == 0) {
                destroy(command[0], command[1], command[2]);
            } else {
                build(command[0], command[1], command[2]);
            }
        }
        
        return getAnswer();
    }
    
    private int[][] getAnswer() {
        final List<int[]> answer = new ArrayList<>();
        
        for(int x = 0; x <= N; x++) {
            for(int y = 0; y <= N; y++) {
                // 기둥
                if(y + 1 <= N && board[x][y][x][y + 1]) {
                    answer.add(new int[] {x, y, PILLAR});
                }
                // 보
                if(x + 1 <= N && board[x][y][x + 1][y]) {
                    answer.add(new int[] {x, y, CARPET});
                }
            }
        }
        
        return answer.stream().toArray(int[][]::new);
    }
    
    private boolean checkAll() {
        for(int x = 0; x <= N; x++) {
            for(int y = 0; y <= N; y++) {
                // 기둥
                if(y + 1 <= N && board[x][y][x][y + 1]) {
                    if(!canBuild(x, y, PILLAR)) return false;
                }
                // 보
                if(x + 1 <= N && board[x][y][x + 1][y]) {
                    if(!canBuild(x, y, CARPET)) return false;
                }
            }
        }
        return true;
    }
    
    private void destroy(final int X, final int Y, final int type) {
        if(type == PILLAR) {
            board[X][Y][X][Y + 1] = false; // 잠시 기둥 제거
            final boolean canDestroy = checkAll();
            board[X][Y][X][Y + 1] = true; // 기둥 복원
            
            if(!canDestroy) return;
            
            board[X][Y][X][Y + 1] = false;
            return;
        }
        
        board[X][Y][X + 1][Y] = false; // 잠시 보 제거
        final boolean canDestroy = checkAll();
        board[X][Y][X + 1][Y] = true; // 보 복원

        if(!canDestroy) return;
        
        board[X][Y][X + 1][Y] = false;
    }
    
    private void build(final int X, final int Y, final int type) {
        if(canBuild(X, Y, type)) {
            if(type == PILLAR) board[X][Y][X][Y + 1] = true;
            else board[X][Y][X + 1][Y] = true;
        }
    }
    
    private boolean canBuild(final int X, final int Y, final int type) {
        if(type == PILLAR) {
            // 바닥 위
            if(Y == 0) return true;
            
            // 보의 한쪽 끝부분 위
            if(X < N && board[X][Y][X + 1][Y]) return true;
            if(X > 0 && board[X - 1][Y][X][Y]) return true;

            // 다른 기둥 위
            if(board[X][Y - 1][X][Y]) return true;
            
            return false;
        }
        
        if(Y == 0) return false;
        
        // 한 쪽 끝 부분이 기둥 위
        if(board[X][Y - 1][X][Y]) return true;
        if(board[X + 1][Y - 1][X + 1][Y]) return true;
        
        // 양쪽 끝 부분이 다른 보에 연결
        if(X > 0 && X < N - 1
           && board[X - 1][Y][X][Y] && board[X + 1][Y][X + 2][Y]) return true;
        
        return false;
    }
}
