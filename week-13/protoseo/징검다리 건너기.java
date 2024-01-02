class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        int l = 0;
        int r = 200000000;
        int runCount = 0;
        
        while (l < r && runCount++ < 50) {
            int m = (l + r) / 2;
            
            int cnt = 0;
            for (int i = 0; i < stones.length; i++) {
                if (stones[i] > m) {
                    cnt = 0;
                } else {
                    cnt++;
                }
                if (cnt == k) {
                    break;
                }
            }
            
            if (cnt == k) {
                r = m;
            } else {
                answer = r;
                l = m;
            }
        }
        return answer;
    }
}