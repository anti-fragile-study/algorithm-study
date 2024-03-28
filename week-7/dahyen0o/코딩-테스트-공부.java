import java.util.*;

class Solution {
    
    private static int ALP = 0;
    private static int COP = 0;
    
    public int solution(final int alp, final int cop, final int[][] problems) {
        // 목표 alp, cop 조사
        for(int idx = 0; idx < problems.length; idx++) {
            ALP = Math.max(ALP, problems[idx][0]);
            COP = Math.max(COP, problems[idx][1]);
        }
        
        final int[][] dp = new int[150 + 1][150 + 1];
        
        // 초기값 지정 (문제를 하나도 풀지 않았을 때)
        for(int a = 0; a < dp.length; a++) {
            for(int c = 0; c < dp[a].length; c++) {
                if(a > alp) {
                    dp[a][c] += a - alp;
                }
                if(c > cop) {
                    dp[a][c] += c - cop;
                }
            }
        }
        
        // 문제를 최소 하나 풀었을 때 값 업데이트
        for(int a = 0; a < dp.length; a++) {
            for(int c = 0; c < dp[a].length; c++) {
                if(dp[a][c] == 0) continue; 
                
                for(final int[] prob : problems) {
                    // 해당 문제를 풀 수 있는지 검사
                    final int beforeA = a - prob[2];
                    final int beforeC = c - prob[3];
                    if(beforeA < 0 || beforeC < 0) continue;
                    if(beforeA < prob[0] || beforeC < prob[1]) continue;
                    
                    for(int aa = beforeA; aa <= a; aa++) {
                        for(int cc = beforeC; cc <= c; cc++) {
                            dp[a][c] = Math.min(dp[a][c], dp[aa][cc] + prob[4]);
                        }
                    }
                    // dp[a][c] = Math.min(dp[a][c], dp[beforeA][beforeC] + prob[4]);
                    // dp[a][c] = Math.min(dp[a][c], dp[beforeA][c] + prob[4]);
                    // dp[a][c] = Math.min(dp[a][c], dp[a][beforeC] + prob[4]);
                }
            }
        }
              
        int answer = dp[ALP][COP];
        // for(int a = ALP; a < dp.length; a++) {
        //     for(int c = COP; c < dp[a].length; c++) {
        //         answer = Math.min(answer, dp[a][c]);
        //     }
        // }

        return answer;
    }
    
    private void print(final int[][] dp) {
        for(int a = 0; a < dp.length; a++) {
            for(int c = 0; c < dp[a].length; c++) {
                System.out.printf("%3d ", dp[a][c]);
            }
            System.out.println();
        }
    }
}
