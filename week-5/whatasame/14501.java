/*
3일이 걸리면 D+0, 1, 2
*/

import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static Pair[] pairs;

    public static int run() {
        // day일날 상담을 할 경우 얻을 수 있는 최대 금액 배열 선언
        int[] maxPrice = new int[n + 1]; // 0 ~ n

        // n일 ~ 1일까지 역순으로 구하기
        for (int day = n; day >= 1; day--) {
            // 상담 완료일이 n + 1이 아니라면 현재 일부터 선택가능한 최대값 더하기
            int endDay = day + pairs[day].time - 1;
            if (endDay <= n) {
                maxPrice[day] += pairs[day].price; // 현재 일 값
                maxPrice[day] += getMax(maxPrice, endDay + 1); // 현재 일 이후에 최대 값
            }
        }

        // 최대값 반환
        int max = 0;
        for (int i = 0; i < maxPrice.length; i++) {
            max = Math.max(max, maxPrice[i]);
        }

        return max;
    }

    public static int getMax(int[] maxPrice, int nextDay) {
        int max = 0;
        for (int i = nextDay; i <= n; i++) {
            max = Math.max(max, maxPrice[i]);
        }

        return max;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        pairs = new Pair[n + 1];
        for (int i = 1; i <= n; i++) {
            String[] input = br.readLine().split(" ");
            pairs[i] = new Pair(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
        }

        int result = run();

        System.out.println(result);
    }

    public static class Pair {

        int time;
        int price;

        public Pair(int time, int price) {
            this.time = time;
            this.price = price;
        }
    }
}
