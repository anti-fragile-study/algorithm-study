import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        var sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            sb.append(find(0, str.length() - 1, 0, str)).append('\n');
        }
        System.out.print(sb);
    }

    public static int find(int s, int e, int remove, String str) {
        if (remove == 2) {
            return 2;
        }
        while (s < e) {
            if (str.charAt(s) == str.charAt(e)) {
                s++;
                e--;
                continue;
            }
            return Math.min(find(s + 1, e, remove + 1, str), find(s, e - 1, remove + 1, str));
        }
        return remove;
    }
}
