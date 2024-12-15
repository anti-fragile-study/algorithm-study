import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println((br.readLine().replaceAll("pi|ka|chu", "").isBlank()) ? "YES" : "NO");
    }
}
