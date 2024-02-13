import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int answer;
    static Map<Character, Integer> chars = new HashMap<>();

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        n = str.length();
        for (int i = 0; i < n; i++) {
            chars.put(str.charAt(i), chars.getOrDefault(str.charAt(i), 0) + 1);
        }
        find(0, '\0');
        System.out.println(answer);
    }

    private static void find(int idx, char prev) {
        if (idx >= n) {
            answer++;
            return;
        }
        for (Map.Entry<Character, Integer> entry : chars.entrySet()) {
            if (idx > 0 && entry.getKey() == prev) {
                continue;
            }
            if (entry.getValue() <= 0) {
                continue;
            }
            chars.put(entry.getKey(), entry.getValue() - 1);
            find(idx + 1, entry.getKey());
            chars.put(entry.getKey(), entry.getValue() + 1);
        }
    }
}
