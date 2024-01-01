import java.util.*;

class Solution {
    
    public int solution(final int N, int[] lost, int[] reserve) {
        final boolean[] doLost = new boolean[N + 1];
        for(int l: lost) doLost[l] = true;
        
        final boolean[] canBorrow = new boolean[N + 1];
        for(int r: reserve) canBorrow[r] = true;
        
        for(int n = 1; n <= N; n++) {
            if(doLost[n] && canBorrow[n]) {
                doLost[n] = false;
                canBorrow[n] = false;
            }
        }
        
        int answer = N;
        for(int n = 1; n <= N; n++) {
            if(!doLost[n]) continue;
            if(n > 1 && canBorrow[n - 1]) {
                canBorrow[n - 1] = false;
                doLost[n] = false;
                continue;
            }
            if(n < N && canBorrow[n + 1]) {
                canBorrow[n + 1] = false;
                doLost[n] = false;
                continue;
            }
        }
        for(int n = 1; n <= N; n++) {
            if(!doLost[n]) continue;
            if(n > 1 && canBorrow[n - 1]) {
                canBorrow[n - 1] = false;
                doLost[n] = false;
                continue;
            }
            if(n < N && canBorrow[n + 1]) {
                canBorrow[n + 1] = false;
                doLost[n] = false;
                continue;
            }
            answer--;
        }
        
        return answer;
    }
}
