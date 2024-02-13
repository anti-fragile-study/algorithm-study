import java.io.*;
import java.util.*;

public class Main {

    static String str;
    static int n;
    static Set<String> answers = new HashSet<>();

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        n = str.length();
        find(0, new char[n], new boolean[n]);
        System.out.println(answers.size());
    }

    private static void find(int idx, char[] ary, boolean[] isUsed) {
        if (idx >= n) {
            char prev = ary[0];
            for (int i = 1; i < n; i++) {
                char now = ary[i];
                if (prev == now) {
                    return;
                }
                prev = now;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(ary[i]);
            }
            answers.add(sb.toString());
            return;
        }
        for (int i = 0; i < n; i++) {
            char now = str.charAt(i);
            if (isUsed[i]) {
                continue;
            }
            if (idx > 0 && now == ary[idx - 1]) {
                continue;
            }
            isUsed[i] = true;
            ary[idx] = now;
            find(idx + 1, ary, isUsed);
            isUsed[i] = false;
        }
    }
}