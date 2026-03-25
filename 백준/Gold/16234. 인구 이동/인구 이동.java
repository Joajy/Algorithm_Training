import java.awt.*;
import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int n, l, r;
    static int[][] area;
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        area = new int[n + 1][n + 1];
        for (int i = 1; i <= n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; ++j) {
                area[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static boolean union() {
        boolean changed = false;
        boolean[][] vis = new boolean[n + 1][n + 1];
        int[][] storePopulation = new int[n + 1][n + 1];
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                storePopulation[i][j] = area[i][j];
            }
        }
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                //이미 bfs로 방문한 경우 skip
                if(vis[i][j]) continue;
                vis[i][j] = true;
                //인접국가 인구차이가 조건 성립할 경우(l <= ... <= r) 국경선 공유
                Deque<Point> q = new ArrayDeque<>();
                Deque<Point> store = new ArrayDeque<>();
                q.add(new Point(i, j));
                store.add(new Point(i, j));
                int total = 0;
                while (!q.isEmpty()) {
                    Point cur = q.poll();
                    total += area[cur.x][cur.y];
                    for (int dir = 0; dir < 4; ++dir) {
                        int nx = cur.x + dx[dir];
                        int ny = cur.y + dy[dir];
                        if(nx < 1 || nx > n || ny < 1 || ny > n) continue;
                        if(vis[nx][ny]) continue;
                        int diff = Math.abs(area[cur.x][cur.y] - area[nx][ny]);
                        if(l <= diff && diff <= r) {
                            store.add(new Point(nx, ny));
                            q.add(new Point(nx, ny));
                            vis[nx][ny] = true;
                        }
                    }
                }
                if(store.size() == 1) continue;
                changed = true;
                total /= store.size();
                while (!store.isEmpty()) {
                    Point cur = store.poll();
                    storePopulation[cur.x][cur.y] = total;
                }
            }
        }
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                area[i][j] = storePopulation[i][j];
            }
        }
        return changed;
    }

    static void solution() {
        for (int day = 0; day < 2000; ++day) {
            if(!union()) {
                System.out.println(day);
                return;
            }
        }
        System.out.println(0);
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}