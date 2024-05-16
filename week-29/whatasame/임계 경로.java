/*
 * 출발 지점에서 도착 지점으로 이동
 * 모든 경로를 모두 고려
 * 가장 늦게 도착하는 사람들의 경로 종류 수 반환
 * */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Main {

    static int N, M;
    static Map<Integer, Set<Node>> graph, rGraph;
    static int[] cost;
    static boolean[] visited;
    static int count = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        int[][] data = new int[M][3];
        for (int i = 0; i < M; i++) {
            String[] split = br.readLine().split(" ");
            data[i][0] = Integer.parseInt(split[0]);
            data[i][1] = Integer.parseInt(split[1]);
            data[i][2] = Integer.parseInt(split[2]);
        }

        int start, end;
        String[] split = br.readLine().split(" ");
        start = Integer.parseInt(split[0]);
        end = Integer.parseInt(split[1]);

        int answer = solution(data, start, end);

        System.out.println(answer);
        System.out.println(count);
    }

    static int solution(int[][] data, int start, int end) {
        // graph, in-degree 초기화
        graph = new HashMap<>();
        rGraph = new HashMap<>();
        int[] inDegree = new int[N + 1];
        for (int i = 0; i < M; i++) {
            int u = data[i][0];
            int v = data[i][1];
            int w = data[i][2]; // weight

            graph.computeIfAbsent(u, k -> new HashSet<>()).add(new Node(v, w));
            rGraph.computeIfAbsent(v, k -> new HashSet<>()).add(new Node(u, w));

            inDegree[v] += 1;
        }

        // 위상 정렬 수행
        cost = new int[N + 1];
        Queue<Integer> q = new LinkedList<>();
        q.add(start);  // 출발 노드 (in-degree 0)
        while (!q.isEmpty()) {
            int now = q.remove();

            for (Node neighbor : graph.getOrDefault(now, Collections.emptySet())) {
                inDegree[neighbor.num]--;

                if (inDegree[neighbor.num] == 0) {
                    q.add(neighbor.num);
                }

                cost[neighbor.num] = Math.max(cost[neighbor.num], cost[now] + neighbor.weight);
            }
        }

        // 개수 세기
        visited = new boolean[N + 1];
        dfs(end);

        return cost[end];
    }

    static void dfs(int node) {
        if (visited[node]) {
            return;
        }
        visited[node] = true;

        for (Node neighbor : rGraph.getOrDefault(node, Collections.emptySet())) {
            if (cost[node] == cost[neighbor.num] + neighbor.weight) {
                count += 1;
                dfs(neighbor.num);
            }
        }
    }

    static class Node {

        int num;
        int weight;

        Node(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }
    }
}
