class Solution {
    public int solution(String t, String p) {
        int answer = 0;

        long target = Long.parseLong(p);
        for (int i = 0; i < t.length() - p.length() + 1; i++) {
            String sub = t.substring(i, i + p.length());
            long result = Long.parseLong(sub);

            if (target >= result) answer++;
        }

        return answer;
    }
}
