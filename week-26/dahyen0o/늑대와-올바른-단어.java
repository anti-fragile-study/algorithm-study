import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static StringBuilder sb;

    static final Map<Character, Integer> counts = new HashMap<>();
    static final char[] words = { 'w', 'o', 'l', 'f' };

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        for (final char word : words) {
            counts.put(word, 0);
        }

        final String line = br.readLine();
        sb.append(check(line)).append("\n");

        bw.write(sb.toString());
        bw.flush();
    }

    private static int check(final String line) {
        for (int i = 0; i < line.length(); i++) {
            final char ch = line.charAt(i);
            for (int w = 0; w < words.length; w++) {
                if (ch != words[w]) {
                    continue;
                }

                if (!empty(w + 1)) {
                    return 0;
                }
                counts.put(ch, counts.get(ch) + 1);
                break;
            }

            if (ch == words[words.length - 1]) { // 마지막
                if (!validExceptLast() || counts.get(ch) > counts.get(words[0])) {
                    return 0;
                }

                if (counts.get(ch) == counts.get(words[0])) {
                    clear();
                }
            }
        }

        return valid() ? 1 : 0;
    }

    private static boolean valid() {
        for (int i = 0; i < words.length - 1; i++) {
            if (counts.get(words[i]) != counts.get(words[i + 1])) {
                return false;
            }
        }
        return true;
    }

    private static void clear() {
        for (final char word : words) {
            counts.put(word, 0);
        }
    }

    private static boolean validExceptLast() {
        for (int i = 0; i < words.length - 2; i++) {
            if (counts.get(words[i]) == 0 || counts.get(words[i]) != counts.get(words[i + 1])) {
                return false;
            }
        }
        return true;
    }

    private static boolean empty(final int start) {
        for (int i = start; i < words.length; i++) {
            if (counts.get(words[i]) > 0) {
                return false;
            }
        }
        return true;
    }
}
