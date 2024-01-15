import java.util.*;

class Solution {
    
    private static final int MAX_N = 1_000_000;
    
    private static int START = 0;
    
    public int[] solution(final int[][] edges) {
        final List<Integer>[] in = new List[MAX_N + 1];
        final List<Integer>[] out = new List[MAX_N + 1];
        
        for(int n = 1; n <= MAX_N; n++) {
            in[n] = new ArrayList<>();
            out[n] = new ArrayList<>();
        }
        
        // edges 정보를 in, out에 저장
        for(final int[] edge : edges) {
            in[edge[1]].add(edge[0]);
            out[edge[0]].add(edge[1]);
        }
        
        final int[] answer = new int[4];
        
        // 중심점 탐색
        // : 나가는 점(out)만 존재
        for(int n = 1; n <= MAX_N; n++) {
            if(in[n].size() == 0 && out[n].size() >= 2) {
                START = n; 
                answer[0] = n; break;
            }
        }
        if(START == 0) {
            throw new RuntimeException("중심점 없음");
        }
        
        // 그래프 탐색
        for(final int start : out[START]) {
            int curr = start;
            while(true) {
                if(out[curr].size() == 0) {
                    answer[2]++; break;
                }
                if(out[curr].size() > 1) {
                    answer[3]++; break;
                }
                curr = out[curr].get(0);
                if(curr == start) {
                    answer[1]++; break;
                }
            }
        }
        
        return answer;
    }
}
