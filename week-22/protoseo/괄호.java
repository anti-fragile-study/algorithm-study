import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (n-- > 0) {
            String str = br.readLine();
            int cnt = 0;
            for (char c : str.toCharArray()) {
                if (c == '(') {
                    cnt++;
                } else if (c == ')') {
                    cnt--;
                }
                if (cnt < 0) {
                    break;
                }
            }
            if (cnt == 0) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }
        System.out.println(sb);
    }
}
