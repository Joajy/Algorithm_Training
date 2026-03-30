import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, answer;
    static int s, e;
    static int[] bluray;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        bluray = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            bluray[i] = Integer.parseInt(st.nextToken());
            s = Math.max(s, bluray[i]);
            e += bluray[i];
        }
    }

    static int calc(int size) {
        int blurayCount = 0, sum = 0;
        for (int i = 0; i < n; ++i) {
            if(sum + bluray[i] <= size) sum += bluray[i];
            // 현재 블루레이 합산 시 minimum을 초과하면, 다음 블루레이에 넣어야 한다
            else {
                ++blurayCount;
                sum = bluray[i];
            }
        }
        if(sum > 0) return blurayCount + 1;
        return blurayCount;
    }

    static void solution() {
        while(s <= e) {
            int mid = (s + e) >> 1;
            int cnt = calc(mid);
            if(cnt <= m) {
                e = mid - 1;
                answer = mid;
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