import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int n, m, l;
    static int[] rest;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        rest = new int[n + 2];
        rest[0] = 0;
        rest[n + 1] = l;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; ++i) {
            rest[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(rest);
    }

    static int getRestCount(int term) {
        int count = 0;
        for (int i = 0; i <= n; ++i) {
            int dist = rest[i + 1] - rest[i];
            if(dist % term == 0) --count;
            dist = dist / term;
            count += dist;
        }
        return count;
    }

    static void solution() {
        int s = 1, e = rest[n + 1];
        int answer = 0;
        while(s <= e) {
            int mid = (s + e) >> 1;
            if(mid == 0) break;
            int count = getRestCount(mid);
            if(count <= m) {
                answer = mid;
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