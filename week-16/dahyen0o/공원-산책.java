import java.util.*;

class Solution {
    
    private static final Map<Character, int[]> dirs = Map.of(
        'N', new int[] {-1, 0},
        'S', new int[] {+1, 0},
        'W', new int[] {0, -1},
        'E', new int[] {0, +1}
    );
    
    private boolean[][] park;
    private int[] dog = new int[2];
    
    public int[] solution(String[] park, String[] routes) {
        this.park = new boolean[park.length][park[0].length()];
                
        for(int r = 0; r < park.length; r++) {
            for(int c = 0; c < park[r].length(); c++) {
                switch(park[r].charAt(c)) {
                    case 'S':
                        dog[0] = r;
                        dog[1] = c;
                        this.park[r][c] = true;
                        break;
                    case 'O':
                        this.park[r][c] = true;
                        break;
                }
            }
        }
        
        for(final String route : routes) {
            final String[] cmd = route.split(" ");
            move(cmd[0].charAt(0), Integer.parseInt(cmd[1]));
        }
        
        return dog;
    }
    
    private void move(final char DIR, final int DEPTH) {
        if(!canMove(DIR, DEPTH)) return;
        
        dog[0] += dirs.get(DIR)[0] * DEPTH;
        dog[1] += dirs.get(DIR)[1] * DEPTH;
    }
    
    private boolean canMove(final char DIR, final int DEPTH) {
        final int[] curr = new int[]{dog[0], dog[1]};
        final int[] dir = dirs.get(DIR);
        
        for(int depth = 0; depth < DEPTH; depth++) {
            curr[0] += dir[0];
            curr[1] += dir[1];
            if(!inBound(curr) || !park[curr[0]][curr[1]]) 
                return false;
        }
        return true;
    }
    
    private boolean inBound(final int[] curr) {
        return curr[0] >= 0 && curr[1] >= 0 && curr[0] < park.length && curr[1] < park[0].length;
    }
}
