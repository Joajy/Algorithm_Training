import java.util.*;
import java.io.*;
import java.awt.Point;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int n, m;
    static int[][] area, dist;
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        area = new int[n + 1][m + 1];
        dist = new int[n + 1][m + 1];
        for (int i = 1; i <= n; ++i) {
            String s = br.readLine();
            for (int j = 1; j <= m; ++j) {
                dist[i][j] = (int)1e9;
                area[i][j] = s.charAt(j - 1) - '0';
            }
        }
        dist[1][1] = 0;
    }

    static void solution() {
        Deque<Point> q = new ArrayDeque<>();
        q.add(new Point(1, 1));
        while (!q.isEmpty()) {
            Point cur = q.poll();
            for (int dir = 0; dir < 4; ++dir) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if(nx < 1 || nx > n || ny < 1 || ny > m) continue;
                if(area[nx][ny] == 1) {
                    if(dist[nx][ny] <= dist[cur.x][cur.y] + 1) continue;
                    dist[nx][ny] = dist[cur.x][cur.y] + 1;
                    q.add(new Point(nx, ny));
                } else {
                    if(dist[nx][ny] <= dist[cur.x][cur.y]) continue;
                    dist[nx][ny] = dist[cur.x][cur.y];
                    q.add(new Point(nx, ny));
                }
            }
        }
        System.out.println(dist[n][m]);
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}