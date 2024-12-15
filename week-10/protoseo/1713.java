import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());
        int[] ary = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Picture[] pictures = new Picture[n];
        for (int i = 0; i < c; i++) {
            int recommender = ary[i];

            if (hasRecommenderInPicture(pictures, recommender)) {
                updateRecommenderInPicture(pictures, recommender);
                continue;
            }
            int idx = changePictureIdx(pictures);
            pictures[idx] = new Picture(i, 1, recommender);
        }
        Arrays.sort(pictures, (o1, o2) -> {
            if (o1 != null && o2 != null) {
                return o1.number - o2.number;
            }
            return 0;
        });

        StringBuilder sb = new StringBuilder();
        for (Picture picture : pictures) {
            if (picture == null) {
                continue;
            }
            sb.append(picture.number).append(' ');
        }
        System.out.println(sb);
    }

    static boolean hasRecommenderInPicture(Picture[] pictures, int recommender) {
        for (Picture picture : pictures) {
            if (picture == null) {
                continue;
            }
            if (picture.number == recommender) {
                return true;
            }
        }
        return false;
    }

    static int changePictureIdx(Picture[] pictures) {
        int idx = 0;
        int minCount = Integer.MAX_VALUE;
        int age = Integer.MAX_VALUE;
        for (int i = 0; i < pictures.length; i++) {
            if (pictures[i] == null) {
                return i;
            }
            if (pictures[i].count < minCount || (pictures[i].count == minCount && pictures[i].age < age)) {
                idx = i;
                minCount = pictures[i].count;
                age = pictures[i].age;
            }
        }
        return idx;
    }

    static void updateRecommenderInPicture(Picture[] pictures, int recommender) {
        for (Picture picture : pictures) {
            if (picture == null) {
                continue;
            }
            if (picture.number == recommender) {
                picture.count++;
                return;
            }
        }
    }
}

class Picture {
    int age;
    int count;
    int number;

    public Picture(int age, int count, int number) {
        this.age = age;
        this.count = count;
        this.number = number;
    }
}
