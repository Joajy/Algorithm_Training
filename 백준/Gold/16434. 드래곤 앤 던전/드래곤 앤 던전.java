import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int n, hATK;
    static long[][] room;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        hATK = Integer.parseInt(st.nextToken());
        room = new long[n][3];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            room[i][0] = Integer.parseInt(st.nextToken()); // 방 상태
            room[i][1] = Integer.parseInt(st.nextToken());
            room[i][2] = Integer.parseInt(st.nextToken());
        }
    }

    static boolean clearDungeon(long hp) {
        long attack = hATK;
        long maxHP = hp;
        for (int i = 0; i < n; ++i) {
            // 몬스터 방
            if (room[i][0] == 1) {
                long mATK = room[i][1];
                long mHP = room[i][2];
                // 1. 몬스터를 죽이기 위해 영웅이 공격해야 하는 횟수 (올림 처리)
                long hitCount = mHP / attack + (mHP % attack == 0 ? 0 : 1);
                // 2. 몬스터가 영웅을 공격하는 횟수는 (hitCount - 1)
                hp -= mATK * (hitCount - 1);
                // 생명력이 0 이하가 되면 클리어 실패
                if (hp <= 0) return false;
            }
            // 포션 방
            else {
                attack += room[i][1];
                hp = Math.min(maxHP, hp + room[i][2]);
            }
        }
        return true;
    }

    static void solution() {
        long s = 0, e = (long)1e18;
        long answer = (long)1e18;
        while (s <= e) {
            long mid = (s + e) >> 1;
            if (clearDungeon(mid)) {
                answer = Math.min(answer, mid);
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}