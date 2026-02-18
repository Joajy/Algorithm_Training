import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, answer;
    static String a, b;
    static boolean[] src, dst;

    static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        src = new boolean[n + 1];
        dst = new boolean[n + 1];
        a = br.readLine();
        b = br.readLine();
        for (int i = 0; i < n; ++i) {
            src[i] = a.charAt(i) == '1';
            dst[i] = b.charAt(i) == '1';
        }
        answer = Integer.MAX_VALUE;
    }

    static void solution() {
        if (n == 2) {
            int cnt = 0;
            for (int i = 0; i < 2; ++i) {
                if (src[i] == dst[i]) ++cnt;
            }
            if (cnt == 1) {
                System.out.println(-1);
            } else if (cnt == 0) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
            return;
        }
        // case 1 : 0번 버튼 클릭 후 시작
        for (int i = 0; i < n; ++i) src[i] = a.charAt(i) == '1';
        for (int i = 0; i < n; ++i) dst[i] = b.charAt(i) == '1';
        answer = Math.min(answer, onAndOff(0));
        // case 2 : 1번 버튼 클릭 후 시작
        for (int i = 0; i < n; ++i) src[i] = a.charAt(i) == '1';
        src[0] = !src[0];

        src[1] = !src[1];
        answer = Math.min(answer, onAndOff(1));
        System.out.println(answer);
    }

    static int onAndOff(int cnt) {
        int count = cnt;
        for (int i = 1; i < n; ++i) {
            if (src[i - 1] == dst[i - 1]) continue;
            for (int j = i - 1; j <= i + 1; ++j) {
                src[j] = !src[j];
            }
            ++count;
        }
        for (int i = 0; i < n; ++i) {
            if (src[i] != dst[i]) return Integer.MAX_VALUE;
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}