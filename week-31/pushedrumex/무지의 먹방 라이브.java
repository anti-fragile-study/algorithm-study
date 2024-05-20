import java.util.*;

class Solution {
    public int solution(int[] food_times, long k) {
        long left = 1;
        long right = Arrays.stream(food_times).max().getAsInt();
        long t = 0;
        long eat = 0;
        while (left <= right) {
            long mid = (left + right) / 2;
            long temp = 0;
            for (int time: food_times) {
                temp += Math.min(mid, time);
            }
            if (temp <= k) {
                t = mid;
                eat = temp;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        k -= eat;
        for (int i=0; i<food_times.length; i++) {
            if (food_times[i] > t) {
                if (k == 0) {
                    return i+1;
                }
                k--;
            }
        }
        return -1;
    }
}