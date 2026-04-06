import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static final int MOD = 1000000;
    static int n;
    static int[][][] dp;      //0: 출석, 1: 지각, 2: 결석

    static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        dp = new int[n + 1][2][3];
    }

    static void solution() {
        // O, L, A
        dp[1][0][0] = 1;
        dp[1][1][0] = 1;
        dp[1][0][1] = 1;
        for (int i = 2; i <= n; ++i) {
            //3연속 결석이 아닌(0, 1, 2연 결석)것과 지각 아닌것의 합
            dp[i][0][0] = (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2]) % MOD;
            //각각 1연속일때와 2연속일때 결석 추가
            dp[i][0][1] = dp[i - 1][0][0] % MOD;
            dp[i][0][2] = dp[i - 1][0][1] % MOD;
            //지각이 추가되거나, 3연속 결석이 아니며 이미 1지각이 있던 경우
            dp[i][1][0] = (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2] +
                           dp[i - 1][1][0] + dp[i - 1][1][1] + dp[i - 1][1][2]) % MOD;
            dp[i][1][1] = dp[i - 1][1][0] % MOD;
            dp[i][1][2] = dp[i - 1][1][1] % MOD;
        }
        int answer = 0;
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 3; ++j) {
                answer += dp[n][i][j];
            }
        }
        System.out.println(answer % MOD);
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}