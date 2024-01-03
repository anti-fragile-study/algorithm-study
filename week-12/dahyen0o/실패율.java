import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        final double[][] results = new double[N][2];
        
        for(int stage = 1; stage <= N; stage++) {
            int fail = 0;
            int total = stages.length;
            
            for(int st : stages) {
                if(st == stage) fail++;
                else if(st < stage) total--;
            }
            
            results[stage - 1][0] = stage;
            if(total > 0) results[stage - 1][1] = fail / (double) total;
        }
        
        Arrays.sort(results, new Comparator<double[]>() {
            @Override
            public int compare(double[] arr1, double[] arr2) {
                if(Double.compare(arr2[1], arr1[1]) == 0) {
                    return Double.compare(arr1[0], arr1[0]);
                }
                return Double.compare(arr2[1], arr1[1]);
            }
        });
        
        final int[] answer = new int[N];
        for(int n = 0; n < N; n++) {
            answer[n] = (int) results[n][0];
        }
        return answer;
    }
}
