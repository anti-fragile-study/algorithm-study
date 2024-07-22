import java.util.*;

class Solution {
    public double[] solution(int k, int[][] ranges) {
        ArrayList<Integer> y = new ArrayList<>();
        y.add(k);
        while (k != 1) {
            if (k % 2 == 0) {
                k /= 2;
            } else {
                k = k * 3 + 1;
            }
            y.add(k);
        }

        double[] sums = new double[y.size()];
        for (int i=0;i<y.size()-1;i++) {
            sums[i+1] = (y.get(i)+y.get(i+1)) / (double)2;
        }
        for (int i=1;i<sums.length;i++) {
            sums[i] += sums[i-1];
        }
        System.out.println(Arrays.toString(sums));
        double[] answer = new double[ranges.length];
        for (int i=0;i<ranges.length;i++) {
            int[] range = ranges[i];
            int x1 = range[0];
            int x2 = sums.length+range[1]-1;

            if (x1 > x2) {
                answer[i] = -1;
            } else if (x1 == x2) {
                answer[i] = 0;
            } else {
                answer[i] = sums[x2] - sums[x1];
            }
        }

        return answer;
    }
}