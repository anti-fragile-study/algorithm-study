import java.util.*;

class Solution {
    
    static class Root {
        int r, c;
        
        Root(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    private static final Root[][] roots = new Root[50][50];
    private static final String[][] values = new String[50][50];
    private static final int SIZE = 50;
    private static final List<String> answer = new ArrayList<>();

    public String[] solution(final String[] commands) {
        
        for(int r = 0; r < SIZE; r++) {
            for(int c = 0; c < SIZE; c++) {
                roots[r][c] = new Root(r, c);
            }
        }
        
        for(String command : commands) {
            final String[] c = command.split(" ");
            if(c[0].equals("PRINT")) {
                print(c);
            } else if(c[0].equals("MERGE")) {
                merge(c);
            } else if(c[0].equals("UNMERGE")) {
                unmerge(c);
            } else if(c.length == 4) {
                updateOne(c);
            } else {
                updateAll(c);
            }
        }
        
        return answer.toArray(new String[answer.size()]);
    }
    
    private void print(final String[] command) {
        final int r = Integer.parseInt(command[1]) - 1;
        final int c = Integer.parseInt(command[2]) - 1;
        
        Root root = findRoot(r, c);
        
        if(values[root.r][root.c] == null) {
            answer.add("EMPTY");
        } else {
            answer.add(values[root.r][root.c]);
        }
    }
    
    private void merge(final String[] command) {
        int r1 = Integer.parseInt(command[1]) - 1;
        int c1 = Integer.parseInt(command[2]) - 1;
        int r2 = Integer.parseInt(command[3]) - 1;
        int c2 = Integer.parseInt(command[4]) - 1;
                
        Root root1 = findRoot(r1, c1);
        Root root2 = findRoot(r2, c2);
        
        if(root1.r == root2.r && root1.c == root2.c) return;
        
        String value1 = values[root1.r][root1.c];
        String value2 = values[root2.r][root2.c];
        
        if(value1 != null) { // 1이 대표
            roots[root2.r][root2.c] = root1;
        } else { // 2가 대표
            roots[root1.r][root1.c] = root2;
        } 
    }
    
    private void unmerge(final String[] command) {
        int rr = Integer.parseInt(command[1]) - 1;
        int cc = Integer.parseInt(command[2]) - 1;
        
        final Root root = findRoot(rr, cc);
        final String value = values[root.r][root.c];
        
        final List<Root> deletes = new ArrayList<>();
        
        for(int r = 0; r < SIZE; r++) {
            for(int c = 0; c < SIZE; c++) {
                Root temp = findRoot(r, c); // 현재 셀의 root
                if(temp.r == root.r && temp.c == root.c) {
                    deletes.add(new Root(r, c));
                }
            }
        }
        
        for(Root cell : deletes) {
            roots[cell.r][cell.c] = cell;
            values[cell.r][cell.c] = null;
        }
        
        values[rr][cc] = value;
    }
    
    private void updateOne(final String command[]) {
        int r = Integer.parseInt(command[1]) - 1;
        int c = Integer.parseInt(command[2]) - 1;
        
        final Root root = findRoot(r, c);
        values[root.r][root.c] = command[3];
    }
    
    private void updateAll(final String command[]) {
        for(int r = 0; r < SIZE; r++) {
            for(int c = 0; c < SIZE; c++) {
                final Root root = findRoot(r, c);
                if(command[1].equals(values[root.r][root.c])) {
                    values[root.r][root.c] = command[2];
                }
            }
        }
    }
    
    private Root findRoot(int r, int c) {
        Root parent = roots[r][c];
        if(parent.r == r && parent.c == c) {
            return parent;
        }
        return roots[r][c] = findRoot(parent.r, parent.c);
    }
    
}
