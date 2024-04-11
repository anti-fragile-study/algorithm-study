import java.io.*;
import java.util.*;

public class Main {

    static Map<String, List<String>> folders = new HashMap<>();
    static Map<String, List<String>> files = new HashMap<>();
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
                List<String> folder = folders.getOrDefault(p, new ArrayList<>());
                folder.add(f);
                folders.put(p, folder);
                continue;
            }
            List<String> file = files.getOrDefault(p, new ArrayList<>());
            file.add(f);
            files.put(p, file);
        }
        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            stk = new StringTokenizer(br.readLine());
            String[] start = stk.nextToken().split("/");
            String[] end = stk.nextToken().split("/");
            String startFolder = start[start.length - 1];
            String endFolder = end[end.length - 1];
            if (folders.containsKey(startFolder)) {
                Set<String> sum = new HashSet<>(folders.remove(startFolder));
                List<String> endFolders = folders.getOrDefault(endFolder, new ArrayList<>());
                sum.addAll(endFolders);
                folders.put(endFolder, new ArrayList<>(sum));
            }
            if (files.containsKey(startFolder)) {
                Set<String> sum = new HashSet<>(files.remove(startFolder));
                List<String> endFolders = files.getOrDefault(endFolder, new ArrayList<>());
                sum.addAll(endFolders);
                files.put(endFolder, new ArrayList<>(sum));
            }
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
        if (folders.containsKey(key)) {
            for (String child : folders.get(key)) {
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
