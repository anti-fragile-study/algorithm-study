import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static StringBuilder sb;

    static List<Integer>[] fromTo;
    static final Set<Integer> StoE = new HashSet<>();

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        fromTo = new List[N + 1];
        for (int i = 0; i < fromTo.length; i++) {
            fromTo[i] = new ArrayList<>();
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            final int a = Integer.parseInt(st.nextToken());
            final int b = Integer.parseInt(st.nextToken());

            fromTo[a].add(b);
            fromTo[b].add(a);
        }

        for (int i = 0; i < fromTo.length; i++) {
            fromTo[i].sort(Comparator.naturalOrder());
        }
        
        st = new StringTokenizer(br.readLine());
        final int S = Integer.parseInt(st.nextToken());
        final int E = Integer.parseInt(st.nextToken());

        final List<Integer> StoEList = bfs(S, E);
        for (int path : StoEList) {
            StoE.add(path);
        }
        StoE.remove(S);
        StoE.remove(E);

        final List<Integer> EtoSList = bfs(E, S);
        sb.append(StoEList.size() + EtoSList.size()).append("\n");

        bw.write(sb.toString());
        bw.flush();
    }

    private static List<Integer> bfs(final int start, final int end) {
        final boolean[] visited = new boolean[fromTo.length];
        visited[start] = true;

        final Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(start, new ArrayList<>()));

        while (!queue.isEmpty()) {
            final Point curr = queue.poll();

            if (curr.num == end) {
                return curr.path;
            }

            for (final int next : fromTo[curr.num]) {
                if (visited[next] || StoE.contains(next))
                    continue;

                visited[next] = true;
                final List<Integer> path = new ArrayList<>(curr.path);
                path.add(curr.num);

                queue.add(new Point(next, path));
            }
        }
        throw new RuntimeException("NO PATH");
    }

    static class Point {

        final int num;
        final List<Integer> path;

        public Point(int num, List<Integer> path) {
            this.num = num;
            this.path = path;
        }
    }
}
