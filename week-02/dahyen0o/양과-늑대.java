import java.util.*;

class Solution {
    
    private static final int SHEEP = 0;
    private static final int WOLF = 1;
    private static final int ROOT_IDX = 0;

    private static List<Integer>[] childrens;
    private static int MAX_SHEEP = 0;

    public int solution(final int[] nodes, final int[][] edges) {
        // childrens 초기화
        childrens = new List[nodes.length];
        for(int c = 0; c < childrens.length; c++) {
            childrens[c] = new ArrayList<>();
        }
        for(final int[] edge: edges) {
            childrens[edge[0]].add(edge[1]);
        }
        
        // MAX_SHEEP 초기화
        for(int node : nodes) {
            if(node == SHEEP) MAX_SHEEP++;
        }
        
        return maxSheep(nodes);
    }
    
    private int maxSheep(final int[] nodes) {
        final Queue<Point> queue = new ArrayDeque<>();
        queue.add(
            new Point(new HashSet<>(childrens[ROOT_IDX]), 
                      (nodes[ROOT_IDX] == SHEEP) ? 1 : 0, 
                      (nodes[ROOT_IDX] == WOLF) ? 1 : 0)
        );
        
        // visited[nextIdxes][wolf]: (nextIdxes, wolf) 정보로 방문했을 때 가장 많은 sheep 개수
        final int[][] visited = new int[(int) Math.pow(2, nodes.length)] // nextIdxes 비트
                                       [nodes.length - MAX_SHEEP + 1]; // wolf 개수
        visited[toBit(queue.peek().nextIdxes)][queue.peek().wolf] = queue.peek().sheep;
        
        int maxSheep = 0;
        
        while(!queue.isEmpty()) {
            final Point curr = queue.poll();
            
            // maxSheep 비교
            maxSheep = Math.max(maxSheep, curr.sheep);
            
            // MAX_SHEEP에 도달했으면 더 이상의 탐색 불필요
            if(maxSheep == MAX_SHEEP) return maxSheep;
            
            for(final int nextIdx : curr.nextIdxes) {
                // next.nextIdxes 초기화
                final Set<Integer> nextIdxes = new HashSet<>(curr.nextIdxes);
                nextIdxes.remove(nextIdx);
                for(final int child : childrens[nextIdx]) {
                    nextIdxes.add(child);
                }
                
                // next 초기화
                final Point next = new Point(
                              nextIdxes, 
                              (nodes[nextIdx] == SHEEP) ? curr.sheep + 1 : curr.sheep, 
                              (nodes[nextIdx] == WOLF) ? curr.wolf + 1 : curr.wolf);
                
                // 늑대가 더 많으면 잡아먹음
                if(next.sheep <= next.wolf) {
                    next.sheep = 0;
                }
                
                // visited 검사
                final int bits = toBit(nextIdxes);
                if(visited[bits][next.wolf] >= next.sheep) continue;
                visited[bits][next.wolf] = next.sheep;
                
                queue.add(next);
            }
        }
        return maxSheep;
    }
    
    private int toBit(final Set<Integer> idxes) {
        int bits = 0;
        for(int idx : idxes) {
            bits |= (1 << idx); 
        }
        return bits;
    }
    
    static class Point {
        
        Set<Integer> nextIdxes;
        int sheep, wolf;
        
        Point(Set<Integer> nextIdxes, int sheep, int wolf) {
            this.nextIdxes = nextIdxes;
            this.sheep = sheep;
            this.wolf = wolf;
        }
    }
}
