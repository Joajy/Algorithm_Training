import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int n, m;
    static int[] rNum;
    static String[] dp;

    static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        rNum = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            rNum[i] = Integer.parseInt(st.nextToken());
        }
        m = Integer.parseInt(br.readLine());
        dp = new String[m + 1];
        Arrays.fill(dp, "");
    }

    static String compare(String a, String b) {
        if(a.length() > b.length()) return a;
        else if(a.length() < b.length()) return b;
        if(a.compareTo(b) >= 0) return a;
        return b;
    }

    static void solution() {
        dp[0] = "0";
        for (int i = n - 1; i >= 0; --i) {
            for (int j = m - rNum[i]; j >= 0; --j) {
                if(i == 0 && dp[j + rNum[i]].equals("0")) continue;
                dp[j] = compare(dp[j], dp[j + rNum[i]] + i);
            }
        }
        System.out.println(dp[0]);
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}