import java.util.*;

class Solution {
    
    int n, m, r, c, k;
    String answer = "impossible";
    ArrayDeque<String> path = new ArrayDeque<>();
    int count = 0;;
    
    Direction[] directions = {
        new Direction("d", 1, 0),
        new Direction("l", 0, -1),
        new Direction("r", 0, 1),
        new Direction("u", -1, 0)
    };

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        x--;
        y--;
        r--;
        c--;
        this.n = n;
        this.m = m;
        this.r = r;
        this.c = c;
        this.k = k;
        
        dfs(new Node(x, y));
        
        return answer;
    }
    
    void dfs(Node now) {
        
        if (!answer.equals("impossible")) {
            return;
        }
        
        if (Math.abs(now.x-r) + Math.abs(now.y-c) > k-count) {
            return;
        }
        
        if ((Math.abs(now.x-r) + Math.abs(now.y-c) + k-count) % 2 != 0) {
            return;
        }
        
        if (count == k) {
            if (now.x == r && now.y == c) {
                answer = String.join("", path);
            }
            return;
        }
        
        for (Direction direction: directions) {
            int nextX = now.x + direction.dx;
            int nextY = now.y + direction.dy;

            if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) {
                continue;
            }
            
            count++;
            path.addLast(direction.name);
            dfs(new Node(nextX, nextY));
            count--;
            path.removeLast();
        }
    }
    
    class Node {
        int x;
        int y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    class Direction {
        String name;
        int dx;
        int dy;
        Direction(String name, int dx, int dy) {
            this.name = name;
            this.dx = dx;
            this.dy = dy;
        }
    }
}