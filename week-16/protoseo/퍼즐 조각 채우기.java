import java.util.*;

class Solution {

    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    int n;
    public int solution(int[][] game_board, int[][] table) {
        n = game_board.length;
        List<Piece> gameBoardPieces = findPieces(game_board, 0);
        List<Piece> tablePieces = findPieces(table, 1);
        gameBoardPieces.forEach(Piece::minimize);
        tablePieces.forEach(Piece::minimize);
        boolean[] tablePieceSelect = new boolean[tablePieces.size()];

        int answer = 0;
        for (int k = 0; k < gameBoardPieces.size(); k++) {
            Piece gameBoardPiece = gameBoardPieces.get(k);
            boolean findSamePiece = false;
            for (int i = 0; !findSamePiece && i < tablePieces.size(); i++) {
                Piece tablePiece  = tablePieces.get(i);
                if (tablePieceSelect[i] || gameBoardPiece.parts.size() != tablePiece.parts.size()) {
                    continue;
                }
                List<Part> gameBoardPieceParts = gameBoardPiece.parts;
                Collections.sort(gameBoardPieceParts);
                for (int j = 0; j < 4; j++) {
                    List<Part> tablePieceParts = tablePiece.parts;
                    Collections.sort(tablePieceParts);
                    boolean isSame = true;
                    for (int l = 0; l < gameBoardPieceParts.size(); l++) {
                        if (gameBoardPieceParts.get(l).x != tablePieceParts.get(l).x || gameBoardPieceParts.get(l).y != tablePieceParts.get(l).y) {
                            isSame = false;
                            break;
                        }
                    }
                    if (isSame) {
                        answer += gameBoardPiece.parts.size();
                        tablePieceSelect[i] = true;
                        findSamePiece = true;
                        break;
                    }
                    tablePiece.rotate();
                }
            }
        }
        return answer;
    }

    private List<Piece> findPieces(int[][] board, int value) {
        List<Piece> pieces = new ArrayList<>();
        boolean[][] isVisited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == value && !isVisited[i][j]) {
                    Deque<int[]> q = new ArrayDeque<>();
                    Piece piece = new Piece();
                    q.add(new int[]{j, i});
                    isVisited[i][j] = true;

                    while (!q.isEmpty()) {
                        int[] p = q.poll();
                        int x = p[0];
                        int y = p[1];
                        piece.addPart(new Part(x, y));

                        for (int k = 0; k < 4; k++) {
                            int xx = x + dx[k];
                            int yy = y + dy[k];

                            if (0 > xx || xx >= n || 0 > yy || yy >= n) {
                                continue;
                            }
                            if (board[yy][xx] != value || isVisited[yy][xx]) {
                                continue;
                            }

                            q.add(new int[]{xx, yy});
                            isVisited[yy][xx] = true;
                        }
                    }
                    pieces.add(piece);
                }
            }
        }
        return pieces;
    }
}

class Piece {
    List<Part> parts = new ArrayList<>();
    int minX = 100;
    int minY = 100;

    public void addPart(Part part) {
        this.parts.add(part);
        this.minX = Math.min(this.minX, part.x);
        this.minY = Math.min(this.minY, part.y);
    }

    public void minimize() {
        parts.forEach(p -> {
            p.x -= minX;
            p.y -= minY;
        });
    }

    public int[] getSum() {
        int[] result = new int[2];
        parts.forEach(p -> {
            result[0] += p.x;
            result[1] += p.y;
        });
        return result;
    }

    public void rotate() {
        this.minX = Integer.MAX_VALUE;
        this.minY = Integer.MAX_VALUE;
        parts.forEach(p -> {
            int temp = p.x;
            p.x = p.y;
            p.y = -temp;
            minX = Math.min(minX, p.x);
            minY = Math.min(minY, p.y);
        });
        this.minimize();
    }
}

class Part implements Comparable<Part> {
    int x;
    int y;

    public Part(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int compareTo(Part p) {
        if (this.x == p.x) {
            return this.y - p.y;
        }
        return this.x - p.x;
    }

}
