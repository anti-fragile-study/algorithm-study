import java.util.*;

public class Edge implements Comparable<Edge> {
    
    int node;
    int cost;

    public Edge(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }
    
    @Override
    public int compareTo(Edge o) {
        return Long.compare(this.cost, o.cost);
    }
}

class Solution {
    
    private List<Edge>[] edgeInfo;
    
    public int[] solution(final int N, int[][] paths, int[] gates, int[] summits) { 
        // 1. gate, summit 여부 표시
        final boolean[] isSummit = new boolean[N + 1];
        for(final int summit : summits) {
            isSummit[summit] = true;
        } 
        final boolean[] isGate = new boolean[N + 1];
        for(final int gate : gates) {
            isGate[gate] = true;
        }  
        
        // edges[start] = start 지점에서 시작하는 edge 리스트
        // 2. edges 초기화
        edgeInfo = new ArrayList[N + 1];
        for(int n = 0; n < edgeInfo.length; n++) {
            edgeInfo[n] = new ArrayList<>();
        }
        
        for(int[] path : paths) {
            if(!isGate[path[1]]) { 
                // path[1]이 출발점이 아니면 도착할 수 있음
                edgeInfo[path[0]].add(new Edge(path[1], path[2]));
            }
            if(!isSummit[path[1]]) {
                // path[1]이 도착점이 아니면 출발할 수 있음
                edgeInfo[path[1]].add(new Edge(path[0], path[2]));
            }
        }  
        
        // 3. 다익스트라
        final int[] intensities = new int[N + 1]; 
        Arrays.fill(intensities, Integer.MAX_VALUE);
        
        final Queue<Edge> pq = new PriorityQueue<>();
        // gates를 출발점으로
        for(final int gate : gates) {
            pq.add(new Edge(gate, 0));
        }
    
        final int[] answer = new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE};
        
        while(!pq.isEmpty()) {
            final Edge curr = pq.poll();
            intensities[curr.node] = curr.cost;
            
            // 앞으로의 cost가 무조건 현재 answer보다 크므로 종료
            if(curr.cost > answer[1]) return answer;
            
            if(isSummit[curr.node]) { // 도착점 도달
                if(curr.cost == answer[1]) {
                    answer[0] = Math.min(curr.node, answer[0]);
                } else if(curr.cost < answer[1]) {
                    answer[1] = curr.cost;
                    answer[0] = curr.node;
                }
                // cost가 같으면서 더 작은 node가 답이 될 수 있으므로 계속함
                continue;
            }
            
            for(Edge next : edgeInfo[curr.node]) {
                if(intensities[next.node] > Math.max(curr.cost, next.cost)) {
                    pq.add(new Edge(next.node, Math.max(curr.cost, next.cost)));
                }
            }
        }
        
        return answer;
    }
}
