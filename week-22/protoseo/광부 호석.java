import java.io.*;
import java.util.*;

public class Main {

    static List<Mineral>[] minerals = new List[100002];

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i <= 100001; i++) {
            minerals[i] = new ArrayList<>();
        }

        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int c = Integer.parseInt(stk.nextToken());

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            int v = Integer.parseInt(stk.nextToken());
            minerals[x].add(new Mineral(x, y, v));
        }
        for (int i = 0; i <= 100001; i++) {
            minerals[i].sort(Comparator.naturalOrder());
        }

        long sum = 0;
        long answer = 0;
        int count = 0;
        int nowX = 0;
        int nowY = 100000;

        while (nowX <= 100000 && nowY >= 0) {
            if (count > c) {
                nowY--;
                for (Mineral mineral : minerals[nowX]) {
                    if (mineral.y <= nowY) {
                        break;
                    }
                    sum -= mineral.v;
                    count--;
                }
            } else {
                nowX++;
                for (Mineral mineral : minerals[nowX]) {
                    if (mineral.y > nowY) {
                        continue;
                    }
                    sum += mineral.v;
                    count++;
                }
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
        return o.y - this.y;
    }
}
