import java.util.*;

class Solution {
    
    private static final int[] discountRate = {10, 20, 30, 40};
    
    private static int[] answer = new int[2];

    public int[] solution(int[][] users, int[] emoticons) {
        final int[] discountPerm = new int[emoticons.length];
        perm(0, discountPerm, users, emoticons);
        
        return answer;
    }
    
    private void perm(
        final int idx, final int[] discountPerm, final int[][] users, final int[] emoticons
    ) {
        if(idx == discountPerm.length) {
            // 할인율 계산 완료
            int plusCount = 0;
            int totalSales = 0;
            
            for(int userIdx = 0; userIdx < users.length; userIdx++) {
                final int userDiscountRate = users[userIdx][0];
                final int userPlusCost = users[userIdx][1];
                
                int sum = 0;
                for(int disIdx = 0; disIdx < discountPerm.length; disIdx++) {
                    if(discountPerm[disIdx] < userDiscountRate) {
                        continue;
                    }
                    sum += emoticons[disIdx] * (100 - discountPerm[disIdx]) / 100;
                }
                
                if(sum >= userPlusCost) {
                    plusCount++;
                } else {
                    totalSales += sum;
                }
            }
            
            if(plusCount > answer[0]) {
                answer[0] = plusCount;
                answer[1] = totalSales;
            } else if(plusCount == answer[0]) {
                answer[1] = Math.max(answer[1], totalSales);
            }
            return;
        }
        
        for(int i = 0; i < 4; i++) {
            discountPerm[idx] = discountRate[i];
            perm(idx + 1, discountPerm, users, emoticons);
        }
    }
}
