class Solution {
    public int solution(String t, String p) {
        int N = t.length();
        int M = p.length();
        
        long pLong = Long.parseLong(p);
        int answer = 0;
        for (int i=0;i<=N-M;i++) {
            if (Long.parseLong(t.substring(i, i+M)) <= pLong) {
                answer++;
            }
        }
        return answer;
    }
}