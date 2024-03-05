import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static StringBuilder sb;

    static final char ROOT = 'A';
    static final char NULL = '\u0000';
    static final Map<Character, char[]> tree = new HashMap<>();

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        final int N = Integer.parseInt(br.readLine());

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            final char curr = st.nextToken().charAt(0);
            final char left = st.nextToken().charAt(0);
            final char right = st.nextToken().charAt(0);

            tree.put(curr, new char[2]);
            if (left != '.') {
                tree.get(curr)[0] = left;
            }
            if (right != '.') {
                tree.get(curr)[1] = right;
            }
        }
        
        preorder(ROOT);
        sb.append("\n");

        inorder(ROOT);
        sb.append("\n");

        postorder(ROOT);
        sb.append("\n");

        bw.write(sb.toString());
        bw.flush();
    }

    private static void postorder(final char curr) {
        final char[] children = tree.get(curr);
        if (children[0] != NULL) {
            postorder(children[0]);
        }
        if (children[1] != NULL) {
            postorder(children[1]);
        }
        sb.append(curr);
    }

    private static void inorder(final char curr) {
        final char[] children = tree.get(curr);
        if (children[0] != NULL) {
            inorder(children[0]);
        }
        sb.append(curr);
        if (children[1] != NULL) {
            inorder(children[1]);
        }
    }

    private static void preorder(final char curr) {
        sb.append(curr);
        final char[] children = tree.get(curr);
        if (children[0] != NULL) {
            preorder(children[0]);
        }
        if (children[1] != NULL) {
            preorder(children[1]);
        }
    }
}
