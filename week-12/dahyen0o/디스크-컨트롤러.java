import java.util.*;

class Solution {
    
    public int solution(final int[][] jobs) {
        Arrays.sort(jobs, Comparator.comparingInt(arr -> arr[0]));
        
        final Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
        
        int time = 0;
        int answer = 0;
        int idx = 0;
        while(time <= 1_000 * 1_000) {
            // queue에 현재 시간이거나 이전 시간에 요청된 job들 넣기
            for(; idx < jobs.length; idx++) {
                final int[] job = jobs[idx];
                if(job[0] > time) break;
                queue.add(job);
            }
            
            // 가장 소요시간이 적은 job 작업하기
            if(queue.isEmpty()) {
                time++;
                continue;
            }
            
            int[] job = queue.poll();
            answer += (time - job[0]) + job[1];
            time += job[1];
        }
    
        return answer / jobs.length;
    }
}
