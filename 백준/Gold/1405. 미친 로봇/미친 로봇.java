import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int n;
    static int[] dir;
    static final int[] dx = {0, 0, -1, 1};
    static final int[] dy = {1, -1, 0, 0};
    static boolean[][] vis;
    static double answer;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        dir = new int[4];
        for (int i = 0; i < 4; ++i) {
            dir[i] = Integer.parseInt(st.nextToken());
        }
        vis = new boolean[30][30];
        answer = 0;
    }

    static void move(int x, int y, int cnt, double probability) {
        if(vis[x][y]) return;
        if(cnt == n) {
            answer += probability;
            return;
        }
        vis[x][y] = true;
        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(vis[nx][ny]) continue;
            move(nx, ny, cnt + 1, probability * dir[i] / 100);
        }
        vis[x][y] = false;
    }

    static void solution() {
        for (int i = 0; i < 4; ++i) {
            move(15, 15,  0, (double) dir[i] / 100);
        }
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}