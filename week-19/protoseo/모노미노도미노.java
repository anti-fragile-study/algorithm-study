import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    static int[][] greenMap = new int[6][4];
    static int[][] blueMap = new int[6][4];
    static int score;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        while (n-- > 0) {
            String[] split = br.readLine().split(" ");
            int t = Integer.parseInt(split[0]);
            int x = Integer.parseInt(split[1]);
            int y = Integer.parseInt(split[2]);

            Block greenBlock = setGreenPairs(t, x, y);
            Block blueBlock = convertBlueBlock(greenBlock);

            down(greenMap, greenBlock);
            down(blueMap, blueBlock);
            getScore(greenMap);
            getScore(blueMap);
            remove(greenMap);
            remove(blueMap);
        }

        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (greenMap[i][j] > 0) {
                    count++;
                }
                if (blueMap[i][j] > 0) {
                    count++;
                }
            }
        }
        sb.append(score).append('\n').append(count);
        System.out.println(sb);
    }

    static Block setGreenPairs(int t, int x, int y) {
        if (t == 1) {
            return new Block(t, List.of(new Pair(x, y)));
        } else if (t == 2) {
            return new Block(t, List.of(new Pair(x, y), new Pair(x, y + 1)));
        }
        return new Block(t, List.of(new Pair(x, y), new Pair(x + 1, y)));
    }

    static Block convertBlueBlock(Block greenBlock) {
        List<Pair> bluePairs = greenBlock.pairs.stream().map(p -> new Pair(p.y, 3 - p.x)).collect(Collectors.toList());
        if (greenBlock.t == 1) {
            return new Block(1, bluePairs);
        }
        return new Block(5 - greenBlock.t, bluePairs);
    }

    static void down(int[][] map, Block block) {
        if (block.t == 2) {
            int y1 = block.pairs.get(0).y;
            int y2 = block.pairs.get(1).y;
            for (int i = 0; i < 6; i++) {
                if (map[i][y1] == 0 && map[i][y2] == 0) {
                    if (i == 5) {
                        map[i][y1] = map[i][y2] = 2;
                    }
                    continue;
                }
                map[i - 1][y1] = map[i - 1][y2] = 2;
                break;
            }
        } else {
            for (Pair pair : block.pairs) {
                int y = pair.y;
                for (int i = 0; i < 6; i++) {
                    if (map[i][y] == 0) {
                        if (i == 5) {
                            map[i][y] = 1;
                        }
                        continue;
                    }
                    map[i - 1][y] = 1;
                    break;
                }
            }
        }
    }

    static void getScore(int[][] map) {
        boolean hasScore;
        do {
            hasScore = false;
            for (int i = 0; i < 6; i++) {
                if (map[i][0] > 0 && map[i][1] > 0 && map[i][2] > 0 && map[i][3] > 0) {
                    map[i][0] = map[i][1] = map[i][2] = map[i][3] = 0;
                    hasScore = true;
                    score++;
                    cleanup(map);
                    break;
                }
            }
        } while (hasScore);
    }

    static void cleanup(int[][] map) {
        for (int i = 4; i >= 0; i--) {
            for (int j = 0; j < 4; j++) {
                if (map[i][j] == 2) {
                    map[i][j] = 0;
                    map[i][j + 1] = 0;
                    int y1 = j;
                    int y2 = ++j;
                    for (int k = i + 1; k < 6; k++) {
                        if (map[k][y1] == 0 && map[k][y2] == 0) {
                            if (k == 5) {
                                map[k][y1] = map[k][y2] = 2;
                            }
                            continue;
                        }
                        map[k - 1][y1] = map[k - 1][y2] = 2;
                        break;
                    }
                } else if (map[i][j] == 1) {
                    map[i][j] = 0;
                    int y = j;
                    for (int k = i + 1; k < 6; k++) {
                        if (map[k][y] == 0) {
                            if (k == 5) {
                                map[k][y] = 1;
                            }
                            continue;
                        }
                        map[k - 1][y] = 1;
                        break;
                    }
                }
            }
        }
    }

    static void remove(int[][] map) {
        while (upperThreshold(map)) {
            for (int i = 4; i >= 0; i--) {
                for (int j = 0; j < 4; j++) {
                    map[i + 1][j] = map[i][j];
                }
            }
            for (int j = 0; j < 4; j++) {
                map[0][j] = 0;
            }
        }
    }

    static boolean upperThreshold(int[][] map) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (map[i][j] > 0) {
                    return true;
                }
            }
        }
        return false;
    }
}

class Block {
    int t;
    List<Pair> pairs;

    public Block(int t, List<Pair> pairs) {
        this.t = t;
        this.pairs = pairs;
    }
}

class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
