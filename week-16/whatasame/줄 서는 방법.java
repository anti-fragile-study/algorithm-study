import java.util.*;

class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];

        long total = 1; // 전체 경우의 수
        List<Integer> nums = new ArrayList<>(); // 1 to N;
        for (int i = 1; i <= n; i++) {
            total *= i;
            nums.add(i);
        }

        k--; // 예외 케이스: n = 4, k = 24 -> [4, 3, 2, 1]
        int idx = 0;
        while (n > 0) { // n번째 자리부터 1번째 자리까지
            total /= n;

            // num번째 버킷
            int num = (int) (k / total);
            answer[idx++] = nums.remove(num);

            k %= total;

            n--;
        }

        return answer;
    }
}
