import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            if (Math.abs(o1) == Math.abs(o2)) {
                return o1 - o2;
            }
            return Math.abs(o1) - Math.abs(o2);
        });

        StringBuilder sb = new StringBuilder();
        while (n-- > 0) {
            int a = Integer.parseInt(br.readLine());
            if (a == 0) {
                if (pq.isEmpty()) {
                    sb.append(0);
                } else {
                    sb.append(pq.poll());
                }
                sb.append('\n');
                continue;
            }
            pq.add(a);
        }
        System.out.println(sb);
    }
}
