import java.util.*;

class Solution {
    int[] parents;
    public int solution(int n, int[][] wires) {

        int answer = 10000;

        for (int i = 0; i < n - 1; i++) {
            parents = new int[n + 1];
            Arrays.fill(parents, -1);
            for (int j = 0; j < n - 1; j++) {
                if (i == j) {
                    continue;
                }
                int a = wires[j][0];
                int b = wires[j][1];
                union(a, b);
            }
            int min = 10000;
            int max = 0;
            for (int j = 1; j <= n; j++) {
                if (parents[j] < 0) {
                    min = Math.min(min, -parents[j]);
                    max = Math.max(max, -parents[j]);
                }
            }
            answer = Math.min(answer, max - min);
        }

        return answer;
    }

    int find(int a) {
        if (parents[a] < 0) {
            return a;
        }
        return parents[a] = find(parents[a]);
    }

    boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) {
            return false;
        }
        parents[pb] += parents[pa];
        parents[pa] = pb;
        return true;
    }
}
