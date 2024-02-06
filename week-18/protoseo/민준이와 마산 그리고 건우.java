import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    private static List<Node>[] adjList;
    private static Set<Pair>[] visited;
    private static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int v = split[0];
        int e = split[1];
        int p = split[2];

        adjList = new ArrayList[v + 1];
        visited = new HashSet[v + 1];
        int[] result = new int[v + 1];
        for (int i = 1; i < v + 1; i++) {
            adjList[i] = new ArrayList<>();
            visited[i] = new HashSet<>();
            result[i] = INF;
        }

        for (int i = 0; i < e; i++) {
            split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int a = split[0];
            int b = split[1];
            int w = split[2];
            adjList[a].add(new Node(a, b, w));
            adjList[b].add(new Node(b, a, w));
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        pq.add(new Pair(1, 0));
        result[1] = 0;

        while (!pq.isEmpty()) {
            Pair pair = pq.poll();
            int now = pair.now;
            int weight = pair.weight;

            if (weight > result[now]) {
                continue;
            }
            for (Node node : adjList[now]) {
                int next = node.e;
                int nextWeight = node.w;

                if (result[next] >= result[now] + nextWeight) {
                    result[next] = result[now] + nextWeight;
                    visited[next].add(new Pair(now, result[next]));
                    pq.add(new Pair(next, result[next]));
                }
            }
        }

        Deque<Pair> q = new ArrayDeque<>();
        q.add(new Pair(v, Integer.MAX_VALUE));
        boolean canSave = false;
        while(!q.isEmpty()) {
            Pair pair = q.poll();
            int now = pair.now;
            int weight = pair.weight;
            if (now == p) {
                canSave = true;
                break;
            }
            final List<Pair> pairs = visited[now].stream().sorted((o1, o2) -> o1.weight - o2.weight).collect(Collectors.toList());
            for (Pair next : pairs) {
                if (next.weight <= weight) {
                    weight = next.weight;
                    q.add(new Pair(next.now, next.weight));
                }
            }
        }

        if (canSave) {
            System.out.println("SAVE HIM");
        } else {
            System.out.println("GOOD BYE");
        }
    }
}

class Pair {
    int now;
    int weight;

    public Pair(int now, int weight) {
        this.now = now;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return now == pair.now && weight == pair.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(now, weight);
    }
}

class Node {
    int s;
    int e;
    int w;

    public Node(int s, int e, int w) {
        this.s = s;
        this.e = e;
        this.w = w;
    }
}
