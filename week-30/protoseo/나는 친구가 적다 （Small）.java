import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var s = br.readLine();
        var k = br.readLine();
        var sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                sb.append(c);
            }
        }
        System.out.println((sb.toString().contains(k)) ? 1 : 0);
    }
}
