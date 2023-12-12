class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        int dIdx = -1;
        int pIdx = -1;
        for (int i = 0; i < n; i++) {
            if (deliveries[i] > 0) {
                dIdx = i;
            }
            if (pickups[i] > 0) {
                pIdx = i;
            }
        }
        long answer = 0;
        while (dIdx >= 0 || pIdx >= 0) {
            answer += ((Math.max(dIdx, pIdx) + 1) * 2);
            int temp = 0;
            while (dIdx >= 0 && temp < cap) {
                if (deliveries[dIdx] > 0) {
                    deliveries[dIdx]--;
                    temp++;
                }
                while (dIdx >= 0 && deliveries[dIdx] == 0) {
                    dIdx--;
                }
            }

            temp = 0;
            while (pIdx >= 0 && temp < cap) {
                if (pickups[pIdx] > 0) {
                    pickups[pIdx]--;
                    temp++;
                }
                while (pIdx >= 0 && pickups[pIdx] == 0) {
                    pIdx--;
                }
            }
        }
        return answer;
    }
}
