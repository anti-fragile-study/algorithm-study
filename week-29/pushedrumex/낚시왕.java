import java.io.*;

public class Main {
    static int R, C, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);
        M = Integer.parseInt(input[2]);

        Shark[][] sharks = new Shark[R][C];

        int r, c, s, d, z;
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            r = Integer.parseInt(input[0]);
            c = Integer.parseInt(input[1]);
            s = Integer.parseInt(input[2]);
            d = Integer.parseInt(input[3]);
            z = Integer.parseInt(input[4]);
            sharks[r-1][c-1] = new Shark(s, d, z);
        }

        int answer = 0;
        for (int j = 0; j < C; j++) {
            // 상어 잡기
            for (int i = 0; i < R; i++) {
                if (sharks[i][j] != null) {
                    answer += sharks[i][j].z;
                    sharks[i][j] = null;
                    break;
                }
            }
            sharks = move(sharks);
        }

        System.out.println(answer);
    }

    static Shark[][] move(Shark[][] sharks) {
        Shark[][] updatedSharks = new Shark[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (sharks[i][j] != null) {
                    Shark shark = sharks[i][j];

                    int _i = i;
                    int _j = j;
                    int s;

                    if (shark.d == 1 || shark.d == 2) {
                        s = shark.s % (2 * (R - 1));
                        while (s-- > 0) {
                            if (_i == 0 && shark.d == 1) shark.d = 2;
                            else if (_i == R - 1 && shark.d == 2) shark.d = 1;

                            if (shark.d == 1) _i--;
                            else _i++;
                        }

                    } else {
                        s = shark.s % (2 * (C - 1));
                        while (s-- > 0) {
                            if (_j == 0 && shark.d == 4) shark.d = 3;
                            else if (_j == C - 1 && shark.d == 3) shark.d = 4;

                            if (shark.d == 3) _j++;
                            else _j--;
                        }

                    }

                    if (updatedSharks[_i][_j] != null && updatedSharks[_i][_j].z > shark.z) {
                        continue;
                    }
                    updatedSharks[_i][_j] = shark;
                }
            }
        }
        return updatedSharks;
    }

    static class Shark {
        int s, d, z; // 속력, 방향, 크기

        Shark(int s, int d, int z) {
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

}
