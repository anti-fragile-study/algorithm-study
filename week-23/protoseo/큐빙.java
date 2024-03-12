import java.io.*;
import java.util.*;

public class Main {
    static Map<String, Strategy> strategies = new HashMap<>();

    public static void main(String[] args) throws IOException {
        setStrategies();
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            Cube cube = new Cube(strategies);
            int n = Integer.parseInt(br.readLine());
            StringTokenizer stk = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                cube.rotate(stk.nextToken());
            }
            sb.append(cube.printUpper());
        }
        System.out.println(sb);
    }

    private static void setStrategies() {
        strategies.put("U+", new Strategy(new char[]{'R', 'F', 'L', 'B'}, new char[]{'y', 'y', 'y', 'y'}, new int[]{0, 0, 0, 0}));
        strategies.put("U-", new Strategy(new char[]{'R', 'B', 'L', 'F'}, new char[]{'y', 'y', 'y', 'y'}, new int[]{0, 0, 0, 0}));
        strategies.put("D+", new Strategy(new char[]{'R', 'B', 'L', 'F'}, new char[]{'y', 'y', 'y', 'y'}, new int[]{2, 2, 2, 2}));
        strategies.put("D-", new Strategy(new char[]{'R', 'F', 'L', 'B'}, new char[]{'y', 'y', 'y', 'y'}, new int[]{2, 2, 2, 2}));

        strategies.put("R+", new Strategy(new char[]{'U', 'B', 'D', 'F'}, new char[]{'x', 'x', 'x', 'x'}, new int[]{2, 0, 2, 2}, new boolean[]{true, true, false, false}));
        strategies.put("R-", new Strategy(new char[]{'U', 'F', 'D', 'B'}, new char[]{'x', 'x', 'x', 'x'}, new int[]{2, 2, 2, 0}, new boolean[]{false, false, true, true}));
        strategies.put("L+", new Strategy(new char[]{'U', 'F', 'D', 'B'}, new char[]{'x', 'x', 'x', 'x'}, new int[]{0, 0, 0, 2}, new boolean[]{false, false, true, true}));
        strategies.put("L-", new Strategy(new char[]{'U', 'B', 'D', 'F'}, new char[]{'x', 'x', 'x', 'x'}, new int[]{0, 2, 0, 0}, new boolean[]{true, true, false, false}));

        strategies.put("F+", new Strategy(new char[]{'U', 'R', 'D', 'L'}, new char[]{'y', 'x', 'y', 'x'}, new int[]{2, 0, 0, 2}, new boolean[]{false, true, false, true}));
        strategies.put("F-", new Strategy(new char[]{'U', 'L', 'D', 'R'}, new char[]{'y', 'x', 'y', 'x'}, new int[]{2, 2, 0, 0}, new boolean[]{true, false, true, false}));
        strategies.put("B+", new Strategy(new char[]{'U', 'L', 'D', 'R'}, new char[]{'y', 'x', 'y', 'x'}, new int[]{0, 0, 2, 2}, new boolean[]{true, false, true, false}));
        strategies.put("B-", new Strategy(new char[]{'U', 'R', 'D', 'L'}, new char[]{'y', 'x', 'y', 'x'}, new int[]{0, 2, 2, 0}, new boolean[]{false, true, false, true}));
    }
}

class Cube {
    private final Map<String, Strategy> strategies;
    private char[] colors = {'w', 'y', 'r', 'o', 'g', 'b'};
    private char[] dist = {'U', 'D', 'F', 'B', 'L', 'R'};
    private Map<Character, Integer> idx = new HashMap<>();
    private Side[] sides = new Side[6];

    public Cube(Map<String, Strategy> strategies) {
        this.strategies = strategies;
        for (int i = 0; i < 6; i++) {
            sides[i] = new Side(colors[i]);
            idx.put(dist[i], i);
        }
    }

    public void rotate(String command) {
        int index = idx.get(command.charAt(0));
        if (command.charAt(1) == '+') {
            sides[index].clockwise();
        } else {
            sides[index].counterClockwise();
        }

        Strategy strategy = strategies.get(command);
        char[] temp = sides[idx.get(strategy.order[3])].get(strategy.dist[3], strategy.idx[3], strategy.reverse[3]);
        for (int i = 2; i >= 0; i--) {
            char[] get = sides[idx.get(strategy.order[i])].get(strategy.dist[i], strategy.idx[i], strategy.reverse[i]);
            sides[idx.get(strategy.order[i + 1])].set(strategy.dist[i + 1], strategy.idx[i + 1], get);
        }
        sides[idx.get(strategy.order[0])].set(strategy.dist[0], strategy.idx[0], temp);
    }

    public String printUpper() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(sides[0].map[i][j]);
            }
            sb.append('\n');
        }
        return sb.toString();
    }

}

class Side {
    char[][] map = new char[3][3];

    Side(char c) {
        for (int i = 0; i < 3; i++) {
            Arrays.fill(map[i], c);
        }
    }

    public void clockwise() {
        char[][] temp = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp[j][2 - i] = map[i][j];
            }
        }
        map = temp;
    }

    public void counterClockwise() {
        char[][] temp = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp[2 - j][i] = map[i][j];
            }
        }
        map = temp;
    }

    public char[] get(char c, int i, boolean reverse) {
        char[] result;
        if (c == 'y') {
            result = getY(i);
        } else {
            result = getX(i);
        }
        if (reverse) {
            char tmp = result[0];
            result[0] = result[2];
            result[2] = tmp;
        }
        return result;
    }

    private char[] getY(int y) {
        char[] result = new char[3];
        for (int i = 0; i < 3; i++) {
            result[i] = map[y][i];
        }
        return result;
    }

    private char[] getX(int x) {
        char[] result = new char[3];
        for (int i = 0; i < 3; i++) {
            result[i] = map[i][x];
        }
        return result;
    }

    public void set(char c, int i, char[] ary) {
        if (c == 'y') {
            setY(i, ary);
            return;
        }
        setX(i, ary);
    }

    private void setY(int y, char[] ary) {
        for (int i = 0; i < 3; i++) {
            map[y][i] = ary[i];
        }
    }

    private void setX(int x, char[] ary) {
        for (int i = 0; i < 3; i++) {
            map[i][x] = ary[i];
        }
    }
}

class Strategy {
    char[] order;
    char[] dist;
    int[] idx;
    boolean[] reverse;

    public Strategy(char[] order, char[] dist, int[] idx, boolean[] reverse) {
        this.order = order;
        this.dist = dist;
        this.idx = idx;
        this.reverse = reverse;
    }

    public Strategy(char[] order, char[] dist, int[] idx) {
        this(order, dist, idx, new boolean[]{false, false, false, false});
    }
}
