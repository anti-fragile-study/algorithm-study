import java.util.Arrays;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        
        int answer = 0;
        int l = 0;
        int r = distance;
        while (l <= r) {
            int m = (l + r) / 2;
            
            int prev = 0;
            int count = 0;
            for (int i = 0; i < rocks.length + 1; i++) {
                int now = (i < rocks.length) ? rocks[i] : distance;
                if (now - prev < m) {
                    count++;
                    continue;
                }
                prev = now;
            }
            
            if (count <= n) {
                answer = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        
        return answer;
    }
}