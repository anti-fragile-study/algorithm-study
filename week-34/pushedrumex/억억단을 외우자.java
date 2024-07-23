import java.util.*;

class Solution {
    public int[] solution(int e, int[] starts) {
        int[] count = new int[e+1];
        for (int i=1;i<=e;i++) {
            for (int j=1;j<=e/i;j++) {
                count[i*j]++;
            }
        }
        int maxN = e;
        int maxCount = count[e];
        count[e] = maxN;
        for (int i=e-1;i>0;i--) {
            if (count[i] >= maxCount) {
                maxCount = count[i];
                maxN = i;
            }
            count[i] = maxN;
        }
        ArrayList<Integer> answer = new ArrayList<>();
        for (int start: starts) {
            answer.add(count[start]);
        }
        return answer.stream().mapToInt(x -> x).toArray();
    }
}