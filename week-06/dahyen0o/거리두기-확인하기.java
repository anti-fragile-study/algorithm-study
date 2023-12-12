import java.util.*;

class Solution {
    
    static class Point {
        int r, c;
        
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    private static final int SIZE = 5;
    private static final int DISTANCE = 2;
    private static final int[] dr = {-1, 0, +1, 0};
    private static final int[] dc = {0, +1, 0, -1};

    public int[] solution(final String[][] places) {
        final int[] answer = new int[5];
        
        for(int roomNum = 0; roomNum < places.length; roomNum++) {
            answer[roomNum] = check(places[roomNum]);
        }
        
        return answer;
    }
    
    private int check(final String[] place) {
        final List<Point> people = new ArrayList<>();
        
        for(int r = 0; r < SIZE; r++) {
            for(int c = 0; c < SIZE; c++) {
                if(place[r].charAt(c) == 'P') {
                    people.add(new Point(r, c));
                }
            }
        }
        
        // BFS로 각 사람마다 다른 사람과 거리두기를 지키는 지 확인
        for(Point person : people) {
            if(!bfs(person, place)) {
                return 0;
            }
        }
        
        return 1;
    }
    
    private boolean bfs(final Point start, final String[] room) {
        final boolean[][] isVisited = new boolean[SIZE][SIZE];
        isVisited[start.r][start.c] = true;
        
        final Queue<Point> queue = new ArrayDeque<>();
        queue.add(start);
        
        while(!queue.isEmpty()) {
            final Point curr = queue.poll();
            
            for(int dir = 0; dir < 4; dir++) {
                final int nextR = curr.r + dr[dir];
                final int nextC = curr.c + dc[dir];
                
                if(!canVisit(nextR, nextC) || isVisited[nextR][nextC]) {
                    continue;
                }
                
                if(room[nextR].charAt(nextC) == 'X') {
                    continue;
                }
                
                final Point next = new Point(nextR, nextC);
                final int distance = distance(start, next);
                if(distance > DISTANCE) {
                    continue;
                }
                
                if(room[nextR].charAt(nextC) == 'P') {
                    return false;
                }
                
                queue.add(next);
                isVisited[next.r][next.c] = true;
            }
        }
        return true;
    }
    
    private boolean canVisit(final int r, final int c) {
        return r >= 0 && c >= 0 && r < SIZE && c < SIZE;
    }
    
    private int distance(final Point start, final Point end) {
        return Math.abs(start.r - end.r) + Math.abs(start.c - end.c);
    }
    
}
