import java.io.*;
import java.util.*;

public class Main {

    static List<Mineral>[] xMinerals = new List[100002];
    static List<Mineral>[] yMinerals = new List[100002];

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i <= 100001; i++) {
            xMinerals[i] = new ArrayList<>();
            yMinerals[i] = new ArrayList<>();
        }

        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int c = Integer.parseInt(stk.nextToken());

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            int v = Integer.parseInt(stk.nextToken());
            final Mineral m = new Mineral(x, y, v);
            xMinerals[x].add(m);
            yMinerals[y].add(m);
        }

        for (int i = 0; i <= 100001; i++) {
            xMinerals[i].sort(Comparator.naturalOrder());
            yMinerals[i].sort(Comparator.naturalOrder());
        }

        long sum = 0;
        long answer = 0;
        int count = 0;
        int nowX = 0;
        int nowY = 100001;

        while (nowX <= 100000 && nowY >= 0) {
            if (count > c) {
                for (Mineral mineral : yMinerals[nowY]) {
                    if (mineral.x <= nowX) {
                        sum -= mineral.v;
                        count--;
                    }
                }
                nowY--;
            } else {
                for (Mineral mineral : xMinerals[nowX]) {
                    if (mineral.y <= nowY) {
                        sum += mineral.v;
                        count++;
                    }
                }
                nowX++;
            }
            if (count <= c) {
                answer = Math.max(answer, sum);
            }
        }
        System.out.println(answer);
    }
}

class Mineral implements Comparable<Mineral> {
    int x;
    int y;
    int v;

    public Mineral(int x, int y, int v) {
        this.x = x;
        this.y = y;
        this.v = v;
    }

    @Override
    public int compareTo(Mineral o) {
        if (o.y == this.y) {
            return this.x - o.x;
        }
        return o.y - this.y;
    }
}
