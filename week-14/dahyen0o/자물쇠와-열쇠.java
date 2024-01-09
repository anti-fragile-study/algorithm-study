import java.util.*;

class Solution {
    
    private static int countHomeInLock;
    
    public boolean solution(int[][] key, int[][] lock) {
        countHomeInLock = countHome(lock);
        
        for(int rotate = 0; rotate < 4; rotate++) {
            key = rotate(key);
            if(doMatches(key, lock)) {
                return true;
            }
        }
        return false;
    }
    
    private int[][] rotate(final int[][] arr) {
        // 시계 방향 회전
        final int[][] result = new int[arr.length][arr.length];
        for(int r = 0; r < arr.length; r++) {
            for(int c = 0; c < arr.length; c++) {
                result[c][arr.length - r - 1] = arr[r][c];
            }
        }
        // System.out.println("회전 이후");
        // for(int r = 0; r < result.length; r++) {
        //     for(int c = 0; c < result.length; c++) {
        //         System.out.printf("%2d", result[r][c]);
        //     }
        //     System.out.println();
        // }
        return result;
    }
    
    private boolean doMatches(final int[][] key, final int[][] lock) {
        // 1. lock의 모든 0이 key의 1과 맞음
        // 2. 어떠한 lock의 1도 key의 1과 맞지 않음
        for(int startR = -key.length; startR < lock.length; startR++) {
            for(int startC = -key.length; startC < lock.length; startC++) {
                // key를 움직이며 탐색
                int matches = 0; // 조건 1
                boolean checkDolgi = true; // 조건 2
                for(int r = startR; r < lock.length; r++) {
                    if(!checkDolgi) break;
                    if(r - startR < 0 || r - startR >= key.length) continue;
                    if(r < 0) continue;
                    for(int c = startC; c < lock.length; c++) {
                        if(c - startC < 0 || c - startC >= key.length) continue;
                        if(c < 0) continue;
                        // lock 기준: r, c
                        // key 기준: r - startR, c - startC
                        if(lock[r][c] == 0 && key[r - startR][c - startC] == 1) {
                            matches++;
                            continue;
                        }
                        if(lock[r][c] == 1 && key[r - startR][c - startC] == 1) {
                            checkDolgi = false;
                            break;
                        }
                    }
                }
                
                if(checkDolgi && matches == countHomeInLock) return true;
            }
        }
        return false;
    }
    
    private int countHome(final int[][] arr) {
        int result = 0;
        for(int r = 0; r < arr.length; r++) {
            for(int c = 0; c < arr.length; c++) {
                if(arr[r][c] == 0) result++;
            }
        }
        return result;
    }
}
