import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        while(n-- > 0) {
            int i = Integer.parseInt(br.readLine());
            if (i == 0) {
                sb.append((pq.isEmpty()) ? 0 : pq.poll()).append('\n');
                continue;
            }
            pq.add(i);
        }
        System.out.println(sb);
    }
}
