import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int c, n;
    static int[] dp;
    static int[][] cities;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        cities = new int[n + 1][2];
        dp = new int[c + 100];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            cities[i][0] = Integer.parseInt(st.nextToken());
            cities[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.fill(dp, (int) 1e9);
        dp[0] = 0;
    }

    static void solution() {
        for (int i = 0; i < n; ++i) {
            for (int j = cities[i][1]; j < c + 100; ++j) {
                dp[j] = Math.min(dp[j], dp[j - cities[i][1]] + cities[i][0]);
            }
        }
        int answer = (int)1e9;
        for (int i = c; i < c + 100; ++i) {
            answer = Math.min(answer, dp[i]);
        }
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}