import java.io.*;
import java.util.*;

public class Main {

    static String n;
    static Set<String> answers = new HashSet<>();

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        n = br.readLine();
        for (int i = 0; i < n.length(); i++) {
            find(i, i, "" + n.charAt(i), "" + n.charAt(i));
        }
        System.out.println(answers.size());
    }

    static void find(int l, int r, String target, String total) {
        if (target.equals(n)) {
            answers.add(total);
            return;
        }

        if (l - 1 >= 0) {
            String next = n.charAt(l - 1) + target;
            find(l - 1, r, next, total + next);
        }
        if (r + 1 < n.length()) {
            String next = target + n.charAt(r + 1);
            find(l, r + 1, next, total + next);
        }
    }
}
