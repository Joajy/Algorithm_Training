import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int n, m, t;
    static int[][] area;
    static int[][][] time;
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};
    static class Info{
        int x, y, sword;
        Info(int x, int y, int sword) {
            this.x = x;
            this.y = y;
            this.sword = sword;
        }
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        area = new int[n + 1][m + 1];
        time = new int[n + 1][m + 1][2];
        for (int i = 1; i <= n; ++i) {
            for(int j = 1; j <= m; ++j) {
                for (int k = 0; k < 2; ++k) {
                    time[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        for (int i = 1; i <= n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; ++j) {
                area[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        time[1][1][0] = time[1][1][1] = 0;
    }

    static void solution() {
        Deque<Info> q = new ArrayDeque<>();
        if(area[1][1] == 2) q.add(new Info(1, 1, 1));
        else q.add(new Info(1, 1, 0));
        while (!q.isEmpty()) {
            Info cur = q.poll();
            if(cur.x == n && cur.y == m) continue;
            for (int dir = 0; dir < 4; ++dir) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if(nx < 1 || nx > n || ny < 1 || ny > m) continue;
                int ns = (area[nx][ny] == 2 || cur.sword == 1) ? 1 : 0;
                if(time[nx][ny][ns] <= time[cur.x][cur.y][cur.sword] + 1) continue;
                //벽인 동시에 그람 없을 때
                if(area[nx][ny] == 1 && ns == 0) continue;
                q.add(new Info(nx, ny, ns));
                time[nx][ny][ns] = Math.min(time[nx][ny][ns], time[cur.x][cur.y][cur.sword] + 1);
            }
        }
        int answer = Math.min(time[n][m][0], time[n][m][1]);
        if(answer <= t) System.out.println(answer);
        else System.out.println("Fail");
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}