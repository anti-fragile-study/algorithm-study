class Solution {
    int n, m;
    int[][] map;
    public boolean solution(int[][] key, int[][] lock) {
        n = lock.length;
        m = key.length;
        map = new int[n + (m - 1) * 2][n + (m - 1) * 2];
        for (int i = m - 1; i < m + n - 1; i++) {
            for (int j = m - 1; j < m + n - 1; j++) {
              	map[i][j] = lock[i - m + 1][j - m + 1]; 
            }
        }
        return unlock(key);
    }
    
    private boolean unlock(int[][] key) {
        boolean result = false;
        for (int i = 0; i < m + n - 1; i++) {
            for (int j = 0; j < m + n - 1; j++) {
                for (int k = 0; k < 4; k++) {
                    key = rotate(key);
                    if (canInsert(key, i, j)) {
                        insert(key, i, j);
                        result |= isUnlock();
                        remove(key, i, j);
                    }
                }
            }
        }
        return result;
    }
    
    private boolean canInsert(int[][] key, int y, int x) {
        for (int i = y; i < y + m; i++) {
            for (int j = x; j < x + m; j++) {
                if (key[i - y][j - x] == 1 && map[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private void insert(int[][] key, int y, int x) {
        for (int i = y; i < y + m; i++) {
            for (int j = x; j < x + m; j++) {
                if (key[i - y][j - x] == 1) {
                    map[i][j] = 1;
                }
            }
        }
    }
    
    private void remove(int[][] key, int y, int x) {
        for (int i = y; i < y + m; i++) {
            for (int j = x; j < x + m; j++) {
                if (key[i - y][j - x] == 1) {
                    map[i][j] = 0;
                }
            }
        }
    }
    
    private boolean isUnlock() {
        for (int i = m - 1; i < m + n - 1; i++) {
            for (int j = m - 1; j < m + n - 1; j++) {
                if (map[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private int[][] rotate(int[][] key) {
        int[][] target = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                target[i][j] = key[j][m - i - 1];
            }
        }
        return target;
    }
}