class Solution {
    public int solution(int[] queue1, int[] queue2) {
        long sum = 0;
        long now = 0;
        int l = 0;
        int r = queue1.length - 1;
        int n = queue1.length + queue2.length;
        int[] queue = new int[n];

        for (int i = 0; i < queue1.length; i++) {
            sum += queue1[i];
            queue[i] = queue1[i];
        }
        now = sum;
        for (int i = 0; i < queue2.length; i++) {
            sum += queue2[i];
            queue[i + queue1.length] = queue2[i];
        }

        boolean isAnswer = false;
        int answer = 0;
        while (l <= r && r < n - 1) {
            if (now < (sum / 2)) {
                now += queue[++r];
                answer++;
            } else if (now > (sum / 2)) {
                now -= queue[l++];
                answer++;
            }
            if (now == (sum / 2)) {
                isAnswer = true;
                break;
            }
        }
        if (isAnswer) {
            return answer;
        }
        return -1;
    }
}
