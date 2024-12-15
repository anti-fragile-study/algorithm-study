import java.io.*;
import java.util.*;

public class Main {

    private static List<Pair> virusCandidates = new ArrayList<>();
    static int[][] map;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");

        n = Integer.parseInt(split[0]);
        m = Integer.parseInt(split[1]);
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            split = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(split[j]);
                if (map[i][j] == 2) {
                    virusCandidates.add(new Pair(j, i));
                }
            }
        }

        int result = findStartVirus(0, 0, new boolean[virusCandidates.size()]);
        System.out.println((result == Integer.MAX_VALUE) ? -1 : result);
    }

    static int findStartVirus(int idx, int cnt, boolean[] isUsed) {
        if (cnt >= m) {
            List<Pair> startVirus = new ArrayList<>();
            for (int i = 0; i < virusCandidates.size(); i++) {
                if (isUsed[i]) {
                    startVirus.add(virusCandidates.get(i));
                }
            }
            return bfs(startVirus);
        }

        int result = Integer.MAX_VALUE;
        for (int i = idx; i < virusCandidates.size(); i++) {
            if (isUsed[i]) {
                continue;
            }
            isUsed[i] = true;
            result = Math.min(result, findStartVirus(i + 1, cnt + 1, isUsed));
            isUsed[i] = false;
        }
        return result;
    }

    static int bfs(List<Pair> virus) {
        Deque<Pair> q = new ArrayDeque<>();
        int[][] isVisited = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(isVisited[i], -1);
        }
        for (Pair p : virus) {
            isVisited[p.y][p.x] = 0;
            q.add(p);
        }

        while (!q.isEmpty()) {
            Pair p = q.poll();
            int x = p.x;
            int y = p.y;
            int c = isVisited[y][x];

            for (int i = 0; i < 4; i++) {
                int xx = x + dx[i];
                int yy = y + dy[i];

                if (xx >= n || xx < 0 || yy >= n || yy < 0) {
                    continue;
                }
                if (map[yy][xx] == 1 || isVisited[yy][xx] >= 0) {
                    continue;
                }
                isVisited[yy][xx] = c + 1;
                q.add(new Pair(xx, yy));
            }
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 1 && isVisited[i][j] == -1) {
                    return Integer.MAX_VALUE;
                }
                result = Math.max(result, isVisited[i][j]);
            }
        }

        return result;
    }
}

class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
