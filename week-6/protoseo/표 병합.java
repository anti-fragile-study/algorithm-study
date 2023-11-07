import java.util.*;

class Solution {

    int[] parent = new int[2501];
    String[] blocks = new String[2501];

    public String[] solution(String[] commands) {
        List<String> answerList = new ArrayList<>();
        Arrays.fill(parent, -1);
        Arrays.fill(blocks, "");

        for (String command : commands) {
            String[] split = command.split(" ");
            String cmd = split[0];
            if (cmd.equals("UPDATE") && split.length == 4) {
                int r = Integer.parseInt(split[1]) - 1;
                int c = Integer.parseInt(split[2]) - 1;
                update(r, c, split[3]);
            } else if (cmd.equals("UPDATE") && split.length == 3) {
                update(split[1], split[2]);
            } else if (cmd.equals("MERGE")) {
                int r1 = Integer.parseInt(split[1]) - 1;
                int c1 = Integer.parseInt(split[2]) - 1;
                int r2 = Integer.parseInt(split[3]) - 1;
                int c2 = Integer.parseInt(split[4]) - 1;
                merge(r1, c1, r2, c2);
            } else if (cmd.equals("UNMERGE")) {
                int r = Integer.parseInt(split[1]) - 1;
                int c = Integer.parseInt(split[2]) - 1;
                unmerge(r, c);
            } else if (cmd.equals("PRINT")) {
                int r = Integer.parseInt(split[1]) - 1;
                int c = Integer.parseInt(split[2]) - 1;
                int idx = find(r * 50 + c);
                answerList.add((blocks[idx].equals("")) ? "EMPTY" : blocks[idx]);
            }
        }
        return answerList.stream().toArray(String[]::new);
    }

    void merge(int r1, int c1, int r2, int c2) {
        int idx1 = find(r1 * 50 + c1);
        int idx2 = find(r2 * 50 + c2);
        if (idx1 == idx2) {
            return;
        }
        String value1 = blocks[idx1];
        String value2 = blocks[idx2];
        if (value1.equals("") && !value2.equals("")) {
            blocks[idx1] = value2;
        }
        for (int i = 0; i <= 2500; i++) {
            if (find(i) == idx2) {
                parent[i] = idx1;
            }
        }
    }

    void unmerge (int r, int c) {
        int pa = find(r * 50 + c);
        String value = blocks[pa];
        for (int i = 0; i <= 2500; i++) {
            if (find(i) == pa) {
                parent[i] = -1;
                blocks[i] = "";
            }
        }
        blocks[r * 50 + c] = value;
    }

    void update(int r, int c, String value) {
        blocks[find(r * 50 + c)] = value;
    }

    void update(String value1, String value2) {
        for (int i = 0; i <= 2500; i++) {
            int idx = find(i);
            if (blocks[idx].equals(value1)) {
                blocks[idx] = value2;
            }
        }
    }

    int find(int a) {
        if (parent[a] == -1) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }
}
