import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static StringBuilder sb;

    static int answer;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        final int N = Integer.parseInt(br.readLine());
        final Egg[] eggs = new Egg[N];
        for (int n = 0; n < eggs.length; n++) {
            st = new StringTokenizer(br.readLine());
            eggs[n] = new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        
        // 완탐 (약 500만)
        play(0, eggs);
        
        bw.write(sb.append(answer).append("\n").toString());
        bw.flush();
    }

    private static void play(final int eIdx, final Egg[] eggs) {
        if (eIdx == eggs.length) {
            int crackCount = 0;
            for (final Egg egg : eggs) {
                if (egg.hp <= 0) {
                    crackCount++;
                }
            }
            answer = Math.max(answer, crackCount);
            return;
        }

        if (eggs[eIdx].hp <= 0) {
            play(eIdx + 1, eggs);
            return;
        }
        
        boolean cracked = false;
        for (int idx = 0; idx < eggs.length; idx++) {
            if (idx == eIdx || eggs[idx].hp <= 0) {
                continue;
            }
            eggs[idx].hp -= eggs[eIdx].attack;
            eggs[eIdx].hp -= eggs[idx].attack;

            cracked = true;
            play(eIdx + 1, eggs);

            eggs[idx].hp += eggs[eIdx].attack;
            eggs[eIdx].hp += eggs[idx].attack;
        }
        
        if (!cracked) {
            play(eIdx + 1, eggs);
        }
    }

    private static class Egg {
    
        int hp;
        final int attack;

        public Egg(int hp, int attack) {
            this.hp = hp;
            this.attack = attack;
        }
    }
}
