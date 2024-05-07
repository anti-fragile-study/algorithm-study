import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String K = br.readLine();

        int answer = solution(S, K);

        System.out.println(answer);
    }

    static int solution(String S, String K) {
        StringBuilder sb = new StringBuilder();
        for (char c : K.toCharArray()) {
            sb.append(c);
            sb.append("\\d*");
        }
        String regex = sb.toString();

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(S);

        if (matcher.find()) {
            return 1;
        }

        return 0;
    }
}
