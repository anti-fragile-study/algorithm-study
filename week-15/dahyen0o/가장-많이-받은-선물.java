import java.util.*;

class Solution {
    
    public int solution(String[] friends, String[] gifts) {
        final int[] giftScores = new int[friends.length];
        final int[][] giftInfos = new int[friends.length][friends.length];
        
        final Map<String, Integer> idx = new HashMap<>();
        for(int i = 0; i < friends.length; i++) {
            idx.put(friends[i], i);
        }
        
        for(String gift : gifts) {
            final String[] g = gift.split(" ");
            giftScores[idx.get(g[0])]++;
            giftScores[idx.get(g[1])]--;
            giftInfos[idx.get(g[0])][idx.get(g[1])]++;
        }
        
        int answer = 0;
        for(int i = 0; i < friends.length; i++) {
            int curr = 0;
            for(int j = 0; j < friends.length; j++) {
                if(i == j) continue;
                if(giftInfos[i][j] > giftInfos[j][i]) curr++;
                else if(giftInfos[i][j] == giftInfos[j][i] && giftScores[i] > giftScores[j]) curr++;
            }
            answer = Math.max(answer, curr);
        }
        return answer;
    }
}
