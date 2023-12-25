import java.util.PriorityQueue;
import java.util.Arrays;
// commit for update

class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        int jobCount = jobs.length;
        int answer = 0;
        
        int idx = 0;
        int[] runJob = {0, 0};
        PriorityQueue<int[]> waitQueue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        for (int i = 0; i <= 500000; i++) {
           	while (idx < jobCount && jobs[idx][0] <= i) {
                waitQueue.add(jobs[idx++]);
            }
            if (!waitQueue.isEmpty()) {
                if (runJob[1] > i) {
            	    continue;
            	}
	          	int[] waitedJob = waitQueue.poll();
	            answer += i - waitedJob[0] + waitedJob[1];
	            runJob = new int[]{i, i + waitedJob[1]};   
            }  
        }
        return answer / jobCount;
    }
}