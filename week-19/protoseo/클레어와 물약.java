import java.io.*;
import java.util.*;

public class Main {

    static boolean[] isCreated;
    static Recipe[] recipes;
    static List<Integer>[] ingredientsForRecipe;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);
        isCreated = new boolean[n + 1];
        recipes = new Recipe[m];
        ingredientsForRecipe = new List[n + 1];
        for (int i = 1; i < n + 1; i++) {
            ingredientsForRecipe[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            split = br.readLine().split(" ");
            int k = Integer.parseInt(split[0]);
            int p = Integer.parseInt(split[k + 1]);
            recipes[i] = new Recipe(p, k);
            for (int j = 1; j <= k; j++) {
                int x = Integer.parseInt(split[j]);
                ingredientsForRecipe[x].add(i);
            }
        }

        int l = Integer.parseInt(br.readLine());
        split = br.readLine().split(" ");
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < l; i++) {
            int y = Integer.parseInt(split[i]);
            isCreated[y] = true;
            q.add(y);
        }

        while (!q.isEmpty()) {
            int now = q.poll();
            for (int next : ingredientsForRecipe[now]) {
                int nextPotion = recipes[next].potion;
                if (recipes[next].canCreate()) {
                    if (isCreated[nextPotion]) {
                        continue;
                    }
                    isCreated[nextPotion] = true;
                    q.add(nextPotion);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (isCreated[i]) {
                count++;
                sb.append(i).append(' ');
            }
        }
        sb.insert(0, count + "\n");
        System.out.println(sb);
    }

}

class Recipe {
    int potion;
    int ingredientCount;

    public Recipe(int potion, int ingredientCount) {
        this.potion = potion;
        this.ingredientCount = ingredientCount;
    }

    public boolean canCreate() {
        return --this.ingredientCount == 0;
    }
}
