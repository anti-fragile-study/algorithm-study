class Solution {
    public int solution(int a, int b, int n) {
        int answer = 0;
        while (n >= a) {
            answer += b;
            n += b - a;
        }
        return answer;
    }
}