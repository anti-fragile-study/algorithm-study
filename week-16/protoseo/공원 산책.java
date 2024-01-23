class Solution {
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    
    public int[] solution(String[] park, String[] routes) {
        int n = park.length;
        int m = park[0].length();
        int[] answer = {0, 0};
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (park[i].charAt(j) == 'S') {
                    answer[0] = i;
                    answer[1] = j;
                }
            }
        }
        for (String route : routes) {
            String[] split = route.split(" ");
            int opIdx = getIdx(split[0]);
            int count = Integer.parseInt(split[1]);
            
            int x = answer[1];
            int y = answer[0];
            boolean canMove = true;
            while (count-- > 0) {
                int xx = x + dx[opIdx];
                int yy = y + dy[opIdx];
                if (xx < 0 || yy < 0 || xx >= m || yy >= n || park[yy].charAt(xx) == 'X') {
                    canMove = false;
                    break;
                }
                x = xx;
                y = yy;
            }
            if (canMove) {
                answer[0] = y;
                answer[1] = x;
            }
        }
        return answer;
    }
    
    private int getIdx(String op) {
        if (op.equals("E")) {
            return 0;
        } else if (op.equals("W")) {
            return 1;
        } else if (op.equals("S")) {
            return 2;
        } else {
            return 3;
        }
    }
}