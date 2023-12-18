import java.util.*;

class Solution {
    Deque<Cell> trashcan = new ArrayDeque<>();
    boolean[] isDeleted;
    Cell[] totalCell;
    Cell pointer = null;
    public String solution(int n, int k, String[] cmd) {
        totalCell = new Cell[n];
        isDeleted = new boolean[n];
        for (int i = 0; i < n; i++) {
            totalCell[i] = new Cell(i);
            if (i > 0) {
                totalCell[i - 1].setNext(totalCell[i]);
                totalCell[i].setPrev(totalCell[i - 1]);
            }
            if (i == k) {
                pointer = totalCell[i];
            }
        }

        for (String c : cmd) {
            char detail = c.charAt(0);
            if (detail == 'U') {
                int x = Integer.parseInt(c.split(" ")[1]);
                up(x);
            } else if (detail == 'D') {
                int x = Integer.parseInt(c.split(" ")[1]);
                down(x);
            } else if (detail == 'C') {
                remove();
            } else if (detail == 'Z' && !trashcan.isEmpty()) {
                restore();
            }
        }
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < n; i++) {
            answer.append((isDeleted[i]) ? 'X' : 'O');
        }
        return answer.toString();
    }

    public void up(int x) {
        while (!pointer.isFirstCell() && x > 0) {
            pointer = pointer.prev;
            x--;
        }
    }

    public void down(int x) {
        while (!pointer.isLastCell() && x > 0) {
            pointer = pointer.next;
            x--;
        }
    }

    public void remove() {
        Cell target = pointer;
        if (target.isLastCell()) {
            pointer = target.prev;
            pointer.setNext(null);
        } else if (target.isFirstCell()) {
            pointer = target.next;
            pointer.setPrev(null);
        } else {
            pointer = target.next;
            pointer.setPrev(target.prev);
            target.prev.setNext(pointer);
        }
        trashcan.addLast(target);
        isDeleted[target.idx] = true;
    }

    public void restore() {
        Cell target = trashcan.pollLast();
        isDeleted[target.idx] = false;
        if (!target.isFirstCell()) {
            Cell targetPrev = target.prev;
            targetPrev.setNext(target);
        }
        if (!target.isLastCell()) {
            Cell targetNext = target.next;
            targetNext.setPrev(target);
        }
    }
}

class Cell {
    int idx;
    Cell prev;
    Cell next;

    public Cell(int idx) {
        this.idx = idx;
    }

    public void setPrev(Cell prev) {
        this.prev = prev;
    }

    public void setNext(Cell next) {
        this.next = next;
    }

    public boolean isFirstCell() {
        return this.prev == null;
    }

    public boolean isLastCell() {
        return this.next == null;
    }
}
