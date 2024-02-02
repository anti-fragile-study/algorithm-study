import java.util.*;
import java.util.stream.*;

class Solution {
    private static final int MIN = 0;
    private static final int MAX = 100000;
    Map<Integer, List<Position>> positions = new HashMap<>();
    List<Integer> heights = new ArrayList<>();
    List<Integer> preOrders = new ArrayList<>();
    List<Integer> postOrders = new ArrayList<>();
    Node root;

    public int[][] solution(int[][] nodeinfo) {
        for (int i = 0; i < nodeinfo.length; i++) {
            int idx = i + 1;
            int x = nodeinfo[i][0];
            int y = nodeinfo[i][1];

            List<Position> list = positions.getOrDefault(y, new ArrayList<>());
            list.add(new Position(idx, x, y));
            positions.put(y, list);
            heights.add(y);
        }
        heights = heights.stream().distinct().sorted(((o1, o2) -> o2 - o1)).collect(Collectors.toList());
        for (List<Position> list : positions.values()) {
            list.sort((o1, o2) -> o1.x - o2.x);
        }
        createTree();

        preOrder(root);
        postOrder(root);
        return new int[][]{
                preOrders.stream().mapToInt(i -> i).toArray(), postOrders.stream().mapToInt(i -> i).toArray()
        };
    }

    private void createTree() {
        int height = heights.get(0);
        Position rootPosition = positions.get(height).get(0);
        root = new Node(rootPosition.idx);
        findTree(MIN, rootPosition.x - 1, 1, true, root);
        findTree(rootPosition.x + 1, MAX, 1, false, root);
    }

    private void findTree(int min, int max, int heightIndex, boolean isLeft, Node parent) {
        if (heightIndex >= heights.size() || min > max) {
            return;
        }
        List<Position> list = positions.get(heights.get(heightIndex));
        int l = 0;
        int r = list.size();
        while (l <= r) {
            int m = (l + r) / 2;
            if (m >= list.size()) {
                break;
            }
            Position now = list.get(m);

            if (min <= now.x && now.x <= max) {
                Node child = new Node(now.idx);
                if (isLeft) {
                    parent.l = child;
                } else {
                    parent.r = child;
                }
                findTree(min, now.x - 1, heightIndex + 1, true, child);
                findTree(now.x + 1, max, heightIndex + 1, false, child);
                break;
            } else if (min > now.x) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
    }

    void preOrder(Node node) {
        if (node == null) return;
        preOrders.add(node.idx);
        preOrder(node.l);
        preOrder(node.r);
    }

    void postOrder(Node node) {
        if (node == null) return;
        postOrder(node.l);
        postOrder(node.r);
        postOrders.add(node.idx);
    }
}

class Position {
    int idx;
    int x;
    int y;

    public Position(int idx, int x, int y) {
        this.idx = idx;
        this.x = x;
        this.y = y;
    }
}

class Node {
    int idx;
    Node l;
    Node r;

    public Node(int idx) {
        this.idx = idx;
    }
}
