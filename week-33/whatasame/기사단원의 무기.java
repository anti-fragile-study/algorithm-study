class Solution {
    public int solution(int number, int limit, int power) {
        // 1 ~ 100,000의 약수의 개수를 초기화
        int[] divisors = new int[100_001];
        for (int i = 1; i <= 100_000; i++) {
            int tmp = i;
            while (tmp <= 100_000) {
                divisors[tmp] += 1;
                tmp += i;
            }
        }

        int answer = 0;
        for (int i = 1; i <= number; i++) {
            if (divisors[i] > limit) {
                answer += power;
            } else {
                answer += divisors[i];
            }
        }

        return answer;
    }
}
