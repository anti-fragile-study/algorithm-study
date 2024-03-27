import java.util.*;

class Solution {
    
    public int solution(final int A, final int B, int N) {
        int answer = 0;
        
        while(N >= A) {
            answer += (N / A * B);
            N = (N / A * B) + (N % A);
        }
        
        return answer;
    }
}
