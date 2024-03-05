import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringBuilder sb = new StringBuilder();
        do {
            String[] split = str.split(" ");
            for (int i = split.length - 1; i >= 0; i--) {
                String rev = split[i];
                for (int j = rev.length() - 1; j >= 0; j--) {
                    sb.append(rev.charAt(j));
                }
                sb.append(' ');
            }
            sb.append('\n');
            str = br.readLine();
        } while (!str.equals("END"));
        System.out.print(sb);
    }
}
