import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ20119 {
    
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static StringBuilder sb;

    static Set<Integer>[] recipes;
    static boolean[] canMake;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        recipes = new Set[N + 1];
        for (int i = 1; i < recipes.length; i++) {
            recipes[i] = new HashSet<>();
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            final int K = Integer.parseInt(st.nextToken());

            final int[] recipe = new int[K];
            for (int k = 0; k < K; k++) {
                recipe[k] = Integer.parseInt(st.nextToken());
            }

            final int R = Integer.parseInt(st.nextToken());
            for (int k = 0; k < K; k++) {
                recipes[R].add(recipe[k]);
            }
        }

        final int L = Integer.parseInt(br.readLine());

        canMake = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int l = 0; l < L; l++) {
            canMake[Integer.parseInt(st.nextToken())] = true;
        }
        
        // 분할 정복 & 재귀
        for (int potion = 1; potion <= N; potion++) {
            canMake(potion);
        }

        int count = 0;
        for (int potion = 1; potion <= N; potion++) {
            if (canMake[potion]) {
                count++;
                sb.append(potion).append(" ");
            }
        }

        sb.insert(0, count + "\n");
        
        bw.write(sb.append("\n").toString());
        bw.flush();
    }

    private static boolean canMake(final int potion) {
        if (canMake[potion]) {
            return true;
        }
        
        final Set<Integer> recipe = recipes[potion];
        if (recipe.isEmpty()) {
            return false;
        }

        for (final int ingredient : recipe) {
            if (!canMake(ingredient)) {
                return false;
            }
        }
        return canMake[potion] = true;
    }
}
