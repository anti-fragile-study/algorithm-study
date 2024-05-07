import java.io.*;

public class Main {

    static int r;
    static int c;
    static Shark[][] sharks;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        r = Integer.parseInt(split[0]);
        c = Integer.parseInt(split[1]);
        int m = Integer.parseInt(split[2]);

        sharks = new Shark[r][c];
        for (int i = 0; i < m; i++) {
            split = br.readLine().split(" ");
            int y = Integer.parseInt(split[0]) - 1;
            int x = Integer.parseInt(split[1]) - 1;
            int s = Integer.parseInt(split[2]);
            int d = Integer.parseInt(split[3]);
            int z = Integer.parseInt(split[4]);

            if (d <= 2) {
                s %= 2 * (r - 1);
            } else {
                s %= 2 * (c - 1);
            }
            sharks[y][x] = new Shark(x, y, s, d, z);
        }

        int answer = 0;
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                if (sharks[j][i] == null) {
                    continue;
                }
                answer += sharks[j][i].z;
                sharks[j][i] = null;
                break;
            }
            move();
        }
        System.out.println(answer);
    }

    static void move() {
        Shark[][] movedSharks = new Shark[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (sharks[i][j] == null) {
                    continue;
                }
                final Shark shark = sharks[i][j].move(r, c);
                if (movedSharks[shark.y][shark.x] == null || movedSharks[shark.y][shark.x].z < shark.z) {
                    movedSharks[shark.y][shark.x] = shark;
                }
            }
        }
        sharks = movedSharks;
    }
}

class Shark {
    int x;
    int y;
    int s;
    int d;
    int z;

    public Shark(int x, int y, int s, int d, int z) {
        this.x = x;
        this.y = y;
        this.s = s;
        this.d = d;
        this.z = z;
    }

    public Shark move(int r, int c) {
        int x = this.x;
        int y = this.y;
        int d = this.d;

        if (d == 1) {
            int[] result = verticalMove(y, -1, s, r);
            y = result[0];
            d = (result[1] == -1) ? this.d : 2;
        } else if (d == 2) {
            int[] result = verticalMove(y, 1, s, r);
            y = result[0];
            d = (result[1] == 1) ? this.d : 1;
        } else if (d == 3) {
            int[] result = horizontalMove(x, 1, s, c);
            x = result[0];
            d = (result[1] == 1) ? this.d : 4;
        } else if (d == 4) {
            int[] result = horizontalMove(x, -1, s, c);
            x = result[0];
            d = (result[1] == -1) ?this.d : 3;
        }
        return new Shark(x, y, s, d, z);
    }

    static int[] verticalMove(int y, int v, int s, int r) {
        for (int i = 0; i < s; i++) {
            y += v;
            if (y == -1) {
                v = 1;
                y = 1;
            } else if (y == r) {
                v = -1;
                y = r - 2;
            }
        }
        return new int[]{y, v};
    }

    static int[] horizontalMove(int x, int v, int s, int c) {
        for (int i = 0; i < s; i++) {
            x += v;
            if (x == -1) {
                v = 1;
                x = 1;
            } else if (x == c) {
                v = -1;
                x = c - 2;
            }
        }
        return new int[]{x, v};
    }
}
