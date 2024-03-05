import java.io.*;
import java.util.*;

public class Main {

    private static int[] parent = new int[1001];
    private static char[] types = new char[1001];
    static int n, m;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Arrays.fill(parent, -1);
        StringTokenizer stk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        stk = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            types[i] = stk.nextToken().charAt(0);
        }

        PriorityQueue<Edge> edges = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            edges.add(new Edge(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken())));
        }

        int count = 0;
        int answer = 0;
        while (!edges.isEmpty()) {
            Edge edge = edges.poll();
            int s = edge.s;
            int e = edge.e;
            if (types[s] == types[e]) {
                continue;
            }
            if (union(s, e)) {
                answer += edge.w;
                count++;
            }
            if (count == n - 1) {
                System.out.println(answer);
                return;
            }
        }
        System.out.println(-1);
    }

    private static int find(int a) {
        if (parent[a] < 0) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    private static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) {
            return false;
        }
        parent[pa] = pb;
        return true;
    }
}

class Edge {
    int s;
    int e;
    int w;

    public Edge(int s, int e, int w) {
        this.s = s;
        this.e = e;
        this.w = w;
    }
}
