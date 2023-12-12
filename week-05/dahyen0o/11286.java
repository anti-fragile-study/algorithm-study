import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    static BufferedReader br;
    static StringBuilder answer;
    
    static int N;
    static PriorityQueue<int[]> queue;

    public static void main(String[] args) throws Exception{
        queue = new PriorityQueue<int[]>((o1, o2) -> {
            if(o1[0] == o2[0]) return o1[1] - o2[1];
            else return o1[0] - o2[0];
        });
        answer = new StringBuilder();

        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for(int n = 0; n < N; n++) {
            int num = Integer.parseInt(br.readLine());
            if(num != 0) {
                queue.add(new int[] {Math.abs(num), num});
            } else if (!queue.isEmpty()) {
                answer.append(queue.poll()[1]).append('\n');
            } else {
                answer.append(0).append('\n');
            }
        }

        System.out.print(answer.toString());
    }
}
