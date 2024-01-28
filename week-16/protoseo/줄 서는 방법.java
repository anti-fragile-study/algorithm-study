class Solution {
    
    long[] factorial;
    boolean[] isUsed;
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        isUsed = new boolean[n];
        initFactorial(n);
        
        for (int i = 0; i < n; i++) {
            int order = (int) ((k - 1L) / factorial[n - i - 1]);
            answer[i] = getNumber(order);
            k = (k - 1L) % factorial[n - i - 1] + 1;
        }
        return answer;
    }
    
    private int getNumber(int order) {
        int result = 0;
        int count = -1;
        for (int i = 0; i < isUsed.length; i++) {
            if (!isUsed[i]) {
                count++;
            }
            if (count == order) {
                isUsed[i] = true;
                result = i + 1;
                break;
            }
        }
        return result;
    }
    
    private void initFactorial(int n) {
        factorial = new long[n];
        factorial[0] = 1;
        for (int i = 1; i < n; i++) {
            factorial[i] = i * factorial[i - 1];
        }
    }
}