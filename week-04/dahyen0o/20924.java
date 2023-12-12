import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class Edge {
        int start, end;
        long cost;

        public Edge(int start, int end, long cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static StringBuilder sb;

    static int giga;
    static long maxLengthOfBranch = 0;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int R = Integer.parseInt(st.nextToken());

        final List<Edge>[] edges = new List[N + 1];
        for (int idx = 0; idx < edges.length; idx++) {
            edges[idx] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            final int a = Integer.parseInt(st.nextToken());
            final int b = Integer.parseInt(st.nextToken());
            final int cost = Integer.parseInt(st.nextToken());

            edges[a].add(new Edge(a, b, cost));
            edges[b].add(new Edge(b, a, cost));
        }

        final boolean[] isVisited = new boolean[N + 1];

        sb.append(findPillar(R, edges, isVisited)).append(" ");
        
        findMaxLengthOfBranch(giga, 0, edges, isVisited);
        sb.append(maxLengthOfBranch).append("\n");

        bw.write(sb.toString());
        bw.flush();
    }

    private static void findMaxLengthOfBranch(
            final int curr,
            final long cost,
            final List<Edge>[] edges,
            final boolean[] isVisited) {

        boolean hasChild = false;
        for (Edge next : edges[curr]) {
            if (!isVisited[next.end]) {
                hasChild = true;
                isVisited[next.end] = true;
                findMaxLengthOfBranch(next.end, cost + next.cost, edges, isVisited);
            }

        }

        if (!hasChild) {
            maxLengthOfBranch = Math.max(maxLengthOfBranch, cost);
            return;
        }
    }

    private static long findPillar(final int R, final List<Edge>[] edges, final boolean[] isVisited) {
        isVisited[R] = true;

        if (edges[R].size() >= 2 || edges[R].size() == 0) {
            giga = R;
            return 0;
        }

        int curr = edges[R].get(0).end;
        isVisited[curr] = true;
        long cost = edges[R].get(0).cost;

        while (edges[curr].size() == 2) {
            for (Edge next : edges[curr]) {
                if (!isVisited[next.end]) {
                    curr = next.end;
                    cost += next.cost;
                    isVisited[next.end] = true;
                    giga = next.end;
                    break;
                }
            }
        }

        return cost;
    }
}
