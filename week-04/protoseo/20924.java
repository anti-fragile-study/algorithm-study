import java.io.*;
import java.util.*;

public class Main {

    static List<Node>[] adjList;
    static int n;
    static int r;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] split = br.readLine().split(" ");

        n = Integer.parseInt(split[0]);
        r = Integer.parseInt(split[1]);

        adjList = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            final String[] input = br.readLine().split(" ");
            int s = Integer.parseInt(input[0]);
            int e = Integer.parseInt(input[1]);
            int d = Integer.parseInt(input[2]);
            adjList[s].add(new Node(e, d));
            adjList[e].add(new Node(s, d));
        }

        int boundary = findBoundary();
        boolean[] isVisited = new boolean[n + 1];
        int pillar = getSumLengthBeforeBoundary(r, boundary, isVisited);
        int branch = getMaxLengthNextBoundary(boundary, 0, isVisited);
        System.out.println(pillar + " " + branch);
    }

    static int getSumLengthBeforeBoundary(int s, int e, boolean[] isVisited) {
        int result = 0;
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(s);
        isVisited[s] = true;
        while (!q.isEmpty()) {
            int now = q.poll();
            if (now == e) {
                break;
            }
            for (Node node : adjList[now]) {
                if (isVisited[node.e]) {
                    continue;
                }
                q.add(node.e);
                isVisited[node.e] = true;
                result += node.d;
            }
        }
        return result;
    }

    static int getMaxLengthNextBoundary(int now, int sum, boolean[] isVisited) {
        if (adjList[now].size() == 1) {
            return sum;
        }
        isVisited[now] = true;
        int result = 0;
        for (Node node : adjList[now]) {
            if (isVisited[node.e]) {
                continue;
            }
            result = Math.max(result, getMaxLengthNextBoundary(node.e, sum + node.d, isVisited));
        }
        return result;
    }

    static int findBoundary() {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        boolean[] isVisited = new boolean[n + 1];
        q.add(r);
        isVisited[r] = true;

        int n = r;
        while (!q.isEmpty()) {
            n = q.poll();

            if (n == r && adjList[n].size() >= 2) {
                break;
            } else if (n != r && adjList[n].size() >= 3) {
                break;
            }

            for (Node node : adjList[n]) {
                if (isVisited[node.e]) {
                    continue;
                }
                q.add(node.e);
                isVisited[node.e] = true;
            }
        }
        return n;
    }
}

class Node {

    int e;
    int d;

    public Node(final int e, final int d) {
        this.e = e;
        this.d = d;
    }
}
