import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Set<Integer> volumes = new HashSet<>();
        int[] needs = new int[n];
        for (int i = 0; i < n; i++) {
            needs[i] = Integer.parseInt(br.readLine());
            volumes.add(needs[i]);
        }

        int answer = 0;
        for (int v : volumes) {
            int prev = -1;
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (needs[i] == v) {
                    continue;
                }
                if (prev == needs[i]){
                    count++;
                } else {
                    prev = needs[i];
                    answer = Math.max(answer, count);
                    count = 1;
                }
            }
            answer = Math.max(answer, count);
        }
        System.out.println(answer);
    }
}