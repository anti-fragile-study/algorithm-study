import java.io.*;
import java.util.*;

public class Main {

    static Map<String, List<String>> files = new HashMap<>();
    static Map<String, List<String>> children = new HashMap<>();
    static Map<String, Node> nodes = new HashMap<>();

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        for (int i = 0; i < n + m; i++) {
            stk = new StringTokenizer(br.readLine());
            String p = stk.nextToken();
            String f = stk.nextToken();
            int c = Integer.parseInt(stk.nextToken());
            if (c == 1) {
                List<String> child = children.computeIfAbsent(p, key -> new ArrayList<>());
                child.add(f);
                children.put(p, child);
                continue;
            }
            List<String> file = files.computeIfAbsent(p, key -> new ArrayList<>());
            file.add(f);
            files.put(p, file);
        }
        dfs("main");

        int q = Integer.parseInt(br.readLine());
        var sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            String[] split = br.readLine().split("/");
            String query = split[split.length - 1];
            Node node = nodes.get(query);
            sb.append(node.typeCount).append(' ').append(node.amount).append('\n');
        }
        System.out.print(sb);
    }

    static List<String> dfs(String key) {
        List<String> file = files.getOrDefault(key, new ArrayList<>());
        if (children.containsKey(key)) {
            for (String child : children.get(key)) {
                file.addAll(dfs(child));
            }
        }
        Set<String> fileType = new HashSet<>(file);
        nodes.put(key, new Node(fileType.size(), file.size()));
        return file;
    }
}

class Node {
    int typeCount;
    int amount;

    public Node(int typeCount, int amount) {
        this.typeCount = typeCount;
        this.amount = amount;
    }
}
