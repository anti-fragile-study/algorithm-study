import java.util.*;

class Solution {
    public double[] solution(int k, int[][] ranges) {
        // 우박수열 좌표를 구한다.
        List<Integer> sequence = new ArrayList<>();
        sequence.add(k);
        while (k != 1) {
            if (k % 2 == 0) {
                k /= 2;
            } else {
                k = 3 * k + 1;
            }
            sequence.add(k);
        }
        int n = sequence.size() - 1;

        // 단위 1의 정적분을 dp에 저장한다
        double[] dp = new double[sequence.size()];
        dp[0] = 0; // i == 0 no area
        for (int i = 1; i < dp.length; i++) {
            double area = (double) (sequence.get(i - 1) + sequence.get(i)) * 1 / 2;

            dp[i] = dp[i - 1] + area;
        }

        // dp를 기반으로 면적을 반환한다.
        double[] answer = new double[ranges.length];
        for (int i = 0; i < ranges.length; i++) {
            int a = ranges[i][0];
            int b = ranges[i][1];

            if (a == 0 && b == 0) {
                answer[i] = dp[dp.length - 1];
                continue;
            }

            b += n;
            if (b < a) {
                answer[i] = -1.0;
                continue;
            }

            answer[i] = dp[b] - dp[a];
        }

        return answer;
    }
}
