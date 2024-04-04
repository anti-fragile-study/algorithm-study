import java.io.*;
import java.util.*;

public class Main {
    static TrieNode root;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        var sb = new StringBuilder();
        while (t-- > 0) {
            root = new TrieNode();
            int n = Integer.parseInt(br.readLine());
            for (int i = 0; i < n; i++) {
                String str = br.readLine();
                insert(str);
            }
            int m = Integer.parseInt(br.readLine());
            for (int i = 0; i < m; i++) {
                String str = br.readLine();
                checkDeletable(str);
            }
            sb.append(count(root)).append('\n');
        }
        System.out.print(sb);
    }

    private static void insert(String str) {
        TrieNode current = root;
        for (char c : str.toCharArray()) {
            if (!current.hasChild(c)) {
                current.putChild(c);
            }
            current = current.getChild(c);
        }
        current.isTerminate = true;
    }

    private static void checkDeletable(String str) {
        TrieNode current = root;
        root.isDeletable = false;
        for (char c : str.toCharArray()) {
            current = current.getChild(c);
            if (current == null) {
                return;
            }
            current.isDeletable = false;
        }
    }

    private static int count(TrieNode node) {
        if (node.isDeletable) {
            return 1;
        }
        int result = 0;
        if (node.isTerminate) {
            result++;
        }
        for (TrieNode child : node.child.values()) {
            result += count(child);
        }
        return result;
    }
}

class TrieNode {
    Map<Character, TrieNode> child = new HashMap<>();
    char c;
    boolean isTerminate = false;
    boolean isDeletable = true;

    public TrieNode() {
    }

    public TrieNode(char c) {
        this.c = c;
    }

    boolean hasChild(char c) {
        return child.containsKey(c);
    }

    void putChild(char c) {
        child.put(c, new TrieNode(c));
    }

    TrieNode getChild(char c) {
        return child.get(c);
    }
}
