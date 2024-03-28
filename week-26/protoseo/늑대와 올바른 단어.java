import java.io.*;

public class Main {

    static int[] total = new int[26];

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int n = str.length();

        int answer = 1;
        for (int i = 0; i < n; ) {
            char c = str.charAt(i);
            if (c == 'w') {
                int cnt = 1;
                for (i += 1; i < n; i++) {
                    if (str.charAt(i) == 'w') {
                        cnt++;
                        continue;
                    }
                    break;
                }
                setCount(c, cnt);
            } else if (c == 'o') {
                int cnt = 1;
                for (i += 1; i < n; i++) {
                    if (str.charAt(i) == 'o') {
                        cnt++;
                        continue;
                    }
                    break;
                }
                setCount(c, cnt);
                if (getCount('w') != cnt) {
                    answer = 0;
                    break;
                }
            } else if (c == 'l') {
                int cnt = 1;
                for (i += 1; i < n; i++) {
                    if (str.charAt(i) == 'l') {
                        cnt++;
                        continue;
                    }
                    break;
                }
                if (getCount('w') != cnt || getCount('o') != cnt) {
                    answer = 0;
                    break;
                }
                setCount('l', cnt);
            } else if (c == 'f') {
                int cnt = 1;
                for (i += 1; i < n; i++) {
                    if (str.charAt(i) == 'f') {
                        cnt++;
                        continue;
                    }
                    break;
                }
                if (getCount('w') != cnt || getCount('o') != cnt || getCount('l') != cnt) {
                    answer = 0;
                    break;
                }
                total = new int[26];
            }
        }
        if (getCount('w') == 0 && getCount('o') == 0 && getCount('l') == 0 && getCount('f') == 0) {
            System.out.println(answer);
            return;
        }
        System.out.println(0);
    }

    public static int getCount(char c) {
        return total[c - 'a'];
    }

    public static void setCount(char c, int cnt) {
        total[c - 'a'] = cnt;
    }
}
