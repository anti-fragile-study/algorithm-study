import java.io.*;
import java.util.*;

public class Main {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Map<Integer, Set<Integer>> lovers = new HashMap<>();
        int[][] map = new int[n][n];

        for (int i = 0; i < n * n; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            int now = Integer.parseInt(stk.nextToken());
            Set<Integer> lover = new HashSet<>();
            while (stk.hasMoreTokens()) {
                lover.add(Integer.parseInt(stk.nextToken()));
            }
            lovers.put(now, lover);
            PriorityQueue<Seat> seats = new PriorityQueue<>();
            for (int y = 0; y < n; y++) {
                for (int x = 0; x < n; x++) {
                    if (map[y][x] != 0) {
                        continue;
                    }
                    int e = 0;
                    int l = 0;
                    for (int k = 0; k < 4; k++) {
                        int xx = x + dx[k];
                        int yy = y + dy[k];

                        if (xx < 0 || yy < 0 || xx >= n || yy >= n) {
                            continue;
                        }
                        if (map[yy][xx] == 0) {
                            e++;
                        } else if (lover.contains(map[yy][xx])) {
                            l++;
                        }
                    }
                    seats.add(new Seat(x, y, e, l));
                }
            }
            Seat selected = seats.poll();
            map[selected.y][selected.x] = now;
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int now = map[i][j];
                int cnt = 0;
                Set<Integer> lover = lovers.get(now);
                for (int k = 0; k < 4; k++) {
                    int xx = j + dx[k];
                    int yy = i + dy[k];

                    if (xx < 0 || yy < 0 || xx >= n || yy >= n) {
                        continue;
                    }
                    if (lover.contains(map[yy][xx])) {
                        cnt++;
                    }
                }
                answer += (cnt > 0) ? Math.pow(10, cnt - 1) : 0;
            }
        }
        System.out.println(answer);
    }
}

class Seat implements Comparable<Seat> {
    int x;
    int y;
    int empty;
    int lovers;

    public Seat(int x, int y, int empty, int lovers) {
        this.x = x;
        this.y = y;
        this.empty = empty;
        this.lovers = lovers;
    }

    @Override
    public int compareTo(Seat o) {
        if (this.lovers != o.lovers) {
            return o.lovers - this.lovers;
        }
        if (this.empty != o.empty) {
            return o.empty - this.empty;
        }
        if (this.y != o.y) {
            return this.y - o.y;
        }
        return this.x - o.x;
    }
}
