import java.util.*;

class Solution {
    public int solution(int[] food_times, long k) {
        if (Arrays.stream(food_times).asLongStream().sum() <= k) {
            return -1;
        }

        List<int[]> times = new ArrayList<>();
        for (int i = 0; i < food_times.length; i++) {
            int idx = i + 1;
            int time = food_times[i];
            times.add(new int[]{idx, time});
        }
        times.sort((o1, o2) -> o1[1] - o2[1]);
        
        long deletedTime = 0;
        int prevTime = 0;
        for (int i = 0; i < food_times.length; i++) {
            int[] time = times.get(0);
            long nowDeleteTime = (long) times.size() * (time[1] - prevTime);
            if (deletedTime + nowDeleteTime > k) {
                break;
            }
            deletedTime += nowDeleteTime;
            prevTime = time[1];
            times.remove(time);
        }
        
        times.sort((o1, o2) -> o1[0] - o2[0]);
        return times.get((int)((k - deletedTime) % times.size()))[0];
    }
}