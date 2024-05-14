import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class Main {

    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        int[] answer = solution(nums);

        StringBuilder sb = new StringBuilder();
        for (int a : answer) {
            sb.append(a).append("\n");
        }
        System.out.println(sb);
    }

    /*
     * max heap - center - min heap
     * */
    static int[] solution(int[] nums) {
        int[] answer = new int[N];
        Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        Queue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            if (maxHeap.size() == minHeap.size()) {
                maxHeap.add(nums[i]);
            } else {
                minHeap.add(nums[i]);
            }

            if (!maxHeap.isEmpty() && !minHeap.isEmpty() && maxHeap.element() > minHeap.element()) {
                maxHeap.add(minHeap.remove());
                minHeap.add(maxHeap.remove());
            }

            answer[i] = maxHeap.peek();
        }

        return answer;
    }
}
