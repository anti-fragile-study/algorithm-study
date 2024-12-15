import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(n-- > 0){
            StringTokenizer stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            int[] visited = new int[10000];
            char[] how = new char[10000];
            Arrays.fill(visited,-1);

            ArrayDeque<Integer> q = new ArrayDeque<>();
            q.add(a);
            visited[a] = Integer.MAX_VALUE;
            while(!q.isEmpty()) {
                int now = q.poll();
                if (now == b) {
                    StringBuilder tmp = new StringBuilder();
                    int chk = now;
                    while(chk!=a){
                        tmp.append(how[chk]);
                        chk = visited[chk];
                    }
                    sb.append(tmp.reverse()).append('\n');
                    break;
                }
                int d = (now * 2) % 10000;
                if (visited[d]==-1) {
                    visited[d] = now;
                    how[d] = 'D';
                    q.add(d);
                }
                int s = (now == 0) ? 9999 : now - 1;
                if (visited[s]==-1) {
                    visited[s] = now;
                    how[s] = 'S';
                    q.add(s);
                }
                int l = now / 1000 + (now % 1000) * 10;
                if (visited[l]==-1) {
                    visited[l] = now;
                    how[l]='L';
                    q.add(l);
                }
                int r = (now % 10) * 1000 + (now / 10);
                if (visited[r]==-1) {
                    visited[r] = now;
                    how[r]='R';
                    q.add(r);
                }
            }
        }
        System.out.println(sb);
    }
}