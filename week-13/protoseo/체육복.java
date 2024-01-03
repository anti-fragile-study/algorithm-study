class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] count = new int[n + 2];
        for (int i : lost) {
            count[i]--;
        }
        for (int i : reserve) {
            count[i]++;
        }
        for (int i = 1; i <= n; i++) {
            if (count[i] == -1) {
                if (count[i - 1] > 0) {
                    count[i]++;
                    count[i - 1]--;
                } else if (count[i + 1] > 0) {
                    count[i]++;
                    count[i + 1]--;
                }
            }
        }
        
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (count[i] > -1) {
                answer++;
            }
        }
        return answer;
    }
}