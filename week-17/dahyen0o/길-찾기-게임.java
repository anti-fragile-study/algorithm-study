import java.util.*;

class Solution {

    static class Node {
        final int num;
        final int[] coord;
        Node left, right;

        Node(int num, int[] coord) {
            this.num = num;
            this.coord = coord;
            this.left = null;
            this.right = null;
        }
    }

    private static final Map<String, Integer> toNum = new HashMap<>();

    private static int h = 0; // travel용 history index

    public int[][] solution(final int[][] nodeinfo) {
        // toNum 저장
        for(int num = 0; num < nodeinfo.length; num++) {
            toNum.put(toHash(nodeinfo[num]), num + 1);
        }

        // y축 기준으로 nodeinfo 정렬
        Arrays.sort(nodeinfo, (o1, o2) -> {
            final int y = Integer.compare(o2[1], o1[1]);
            if(y != 0) return y;
            return Integer.compare(o1[0], o2[0]);
        });

        // 트리 생성
        final Node root = new Node(toNum.get(toHash(nodeinfo[0])), nodeinfo[0]);

        for(int i = 1; i < nodeinfo.length; i++) {
            final int[] curr = nodeinfo[i];
            final int currNum = toNum.get(toHash(curr));

            final Node parent = findParent(root, curr);
            if(parent.coord[0] > curr[0]) { // left child
                parent.left = new Node(currNum, curr);
            } else { // right child
                parent.right = new Node(currNum, curr);
            }
        }

        // 트리 순회
        final int[][] answer = new int[2][nodeinfo.length];
        // 전위 순회
        h = 0;
        preTravel(root, answer[0]);
        // 후위 순회
        h = 0;
        postTravel(root, answer[1]);

        return answer;
    }

    private String toHash(final int[] coord) {
        return String.format("%d %d", coord[0], coord[1]);
    }

    private Node findParent(final Node curr, final int[] coord) {
        if(coord[0] < curr.coord[0]) { // left child
            if(curr.left != null) return findParent(curr.left, coord);
            return curr;
        }
        else { // right child
            if(curr.right != null) return findParent(curr.right, coord);
            return curr;
        }
    }

    private void preTravel(final Node curr, final int[] history) {
        if(curr == null) return;

        history[h++] = curr.num;

        preTravel(curr.left, history);
        preTravel(curr.right, history);
    }

    private void postTravel(final Node curr, final int[] history) {
        if(curr == null) return;

        postTravel(curr.left, history);
        postTravel(curr.right, history);
        history[h++] = curr.num;
    }
}
