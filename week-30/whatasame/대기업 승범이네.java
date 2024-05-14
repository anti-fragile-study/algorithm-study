import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Main {

    static int N;
    static Map<Integer, Set<Integer>> tree;
    static Map<Integer, Integer> powerByNum;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        List<Integer> seniors = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt)
                .collect(Collectors.toList());
        List<Integer> powers = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt)
                .collect(Collectors.toList());

        int answer = solution(seniors, powers);

        System.out.println(answer);
    }

    static int solution(List<Integer> seniors, List<Integer> powers) {
        tree = IntStream.range(2, seniors.size() + 2).boxed()
                .collect(Collectors.groupingBy(idx -> seniors.get(idx - 2), Collectors.toSet()));
        powerByNum = IntStream.range(1, powers.size() + 1).boxed()
                .collect(Collectors.toMap(num -> num, num -> powers.get(num - 1)));
        dp = new int[N + 1][2];

        dfs(1);

        return Math.max(dp[1][0], dp[1][1]);
    }

    /*
     * dp[u][0]: u가 멘토가 아닌 경우 = sum of max(dp[v][0], dp[v][1]) for v in children of u
     * dp[u][1]: u가 멘토인 경우 = max(dp[v][0] * dp[u] + exclude sum of v for v in children of u)
     * */
    static void dfs(int node) {
        if (!tree.containsKey(node)) { // leaf node
            return;
        }

        for (int child : tree.get(node)) {
            dfs(child);
            dp[node][0] += Math.max(dp[child][0], dp[child][1]);
        }

        for (int child : tree.get(node)) {
            int relation = powerByNum.get(node) * powerByNum.get(child);
            int excludeSum = dp[node][0] - Math.max(dp[child][0], dp[child][1]) + dp[child][0];
            dp[node][1] = Math.max(dp[node][1], relation + excludeSum);
        }
    }
}
