import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        final int N = Integer.parseInt(br.readLine());
        final int[] nums = new int[N];
        final Set<Integer> numsCheck = new HashSet<>();
        for (int n = 0; n < nums.length; n++) {
            nums[n] = Integer.parseInt(br.readLine());
            numsCheck.add(nums[n]);
        }

        int maxLength = 1;

        for (final int deleted : numsCheck) {
            int length = 1;
            
            int currLength = 1;
            int lastNum = -1;
            for (final int num : nums) {
                if (deleted == num) {
                    continue;
                }
                if (lastNum == num) {
                    currLength++;
                } else {
                    length = Math.max(length, currLength);
                    currLength = 1;
                    lastNum = num;
                }
            }
            length = Math.max(length, currLength);
            
            maxLength = Math.max(maxLength, length);
        }
        
        bw.write(sb.append(maxLength).append("\n").toString());
        bw.flush();
    }
}
