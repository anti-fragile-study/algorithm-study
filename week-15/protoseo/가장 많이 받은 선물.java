import java.util.*;
class Solution {
    public int solution(String[] friends, String[] gifts) {
        int n = friends.length;
        Map<String, Integer> nameToIdx = new HashMap<>();
        int[][] giftTable = new int[n][n];
        int[] giftScore = new int[n];
        for (int i = 0; i < n; i++) {
            nameToIdx.put(friends[i], i);
        }
        
        for (String gift : gifts) {
            String[] split = gift.split(" ");
            int from = nameToIdx.get(split[0]);
            int to = nameToIdx.get(split[1]);
            giftTable[from][to]++;
            giftScore[from]++;
            giftScore[to]--;
        }
        
        int answer = 0;
        for (int i = 0; i < n; i++) {
            int result = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                if (giftTable[i][j] == giftTable[j][i] && giftScore[i] > giftScore[j]) {
                    result++;
                } else if (giftTable[i][j] > giftTable[j][i]) {
                    result++;
                }
            }
            answer = Math.max(answer, result);
        }
        
        return answer;
    }
}