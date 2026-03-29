import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int answer;
    static int[][] result;

    static void input() throws IOException {
        result = new int[6][3];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 6; ++i) {
            result[i][0] = Integer.parseInt(st.nextToken());
            result[i][1] = Integer.parseInt(st.nextToken());
            result[i][2] = Integer.parseInt(st.nextToken());
        }
        answer = 0;
    }

    static void bt(int wIdx, int lIdx) {
        if (answer == 1) return;

        // 모든 팀의 경기가 종료되는 시점
        if (wIdx == 5) {
            for (int i = 0; i < 6; i++) {
                // 승, 무, 패 모두 0이 되어야 올바른 결과
                if (result[i][0] != 0 || result[i][1] != 0 || result[i][2] != 0) return;
            }
            answer = 1;
            return;
        }

        // 상대 팀 번호가 범위를 벗어나면 다음 팀으로 이동
        if (lIdx == 6) {
            bt(wIdx + 1, wIdx + 2);
            return;
        }

        // 1. wIdx 승 / lIdx 패
        if (result[wIdx][0] > 0 && result[lIdx][2] > 0) {
            result[wIdx][0]--; result[lIdx][2]--;
            bt(wIdx, lIdx + 1);
            result[wIdx][0]++; result[lIdx][2]++;
        }
        // 2. 무승부
        if (result[wIdx][1] > 0 && result[lIdx][1] > 0) {
            result[wIdx][1]--; result[lIdx][1]--;
            bt(wIdx, lIdx + 1);
            result[wIdx][1]++; result[lIdx][1]++;
        }
        // 3. wIdx 패 / lIdx 승
        if (result[wIdx][2] > 0 && result[lIdx][0] > 0) {
            result[wIdx][2]--; result[lIdx][0]--;
            bt(wIdx, lIdx + 1);
            result[wIdx][2]++; result[lIdx][0]++;
        }
    }

    static int solution() {
        for (int i = 0; i < 6; i++) {
            // 한 팀의 경기 수 합은 반드시 5여야 함
            if (result[i][0] + result[i][1] + result[i][2] != 5) return 0;
        }
        bt(0, 1);
        return answer;
    }

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 4; i++) {
            input();
            System.out.print(solution() + " ");
        }
    }
}