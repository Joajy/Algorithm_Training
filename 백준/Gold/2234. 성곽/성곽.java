import java.awt.Point;
import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int n, m;
    static int[] answer;
    static int[][] area, number;
    static List<Integer> areaSize;
    static final int[] dx = {0, -1, 0, 1};
    static final int[] dy = {-1, 0, 1, 0};

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        area = new int[n][m];
        number = new int[n][m];
        for(int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; ++j) {
                area[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        answer = new int[3];
        areaSize = new ArrayList<>();
    }

    static void solution() {
        // 1, 2번
        solve();
        // 3번 -> 벽 하나 제거해서 얻을 수 있는 넓은 방의 크기
        breakWall();
        for (int ans : answer) {
            System.out.println(ans);
        }
    }

    static void breakWall() {
        // 모든 칸을 한 번씩만 순회합니다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 현재 칸에서 4방향 확인
                for (int dir = 0; dir < 4; dir++) {
                    int nx = i + dx[dir];
                    int ny = j + dy[dir];
                    // 범위를 벗어나면 제외
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                    // 인접한 칸이 나와 같은 방 번호를 가지고 있다면 (벽이 있다는 뜻 포함)
                    if(number[i][j] == number[nx][ny]) continue;
                    // 두 방의 크기 합산 (number는 1부터 시작하므로 인덱스는 -1)
                    int combinedSize = areaSize.get(number[i][j] - 1) + areaSize.get(number[nx][ny] - 1);
                    answer[2] = Math.max(answer[2], combinedSize);
                }
            }
        }
    }

    static void solve() {
        boolean[][] vis = new boolean[n][m];
        int num = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if(vis[i][j]) continue;
                vis[i][j] = true;
                int size = 1;
                number[i][j] = ++num;
                Deque<Point> q = new ArrayDeque<>();
                q.add(new Point(i, j));
                while (!q.isEmpty()) {
                    Point cur = q.poll();
                    for (int dir = 0; dir < 4; ++dir) {
                        int nx = cur.x + dx[dir];
                        int ny = cur.y + dy[dir];
                        //영역 밖인 경우 제외
                        if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                        //방문했거나 벽에 가로막힌 경우 제외
                        if(vis[nx][ny] || (area[cur.x][cur.y] & (1 << dir)) > 0) continue;
                        // 색칠되어있는데 다른 영역인 경우 배제
                        if(number[nx][ny] > 0 && number[nx][ny] != number[cur.x][cur.y]) continue;
                        number[nx][ny] = num;
                        q.add(new Point(nx, ny));
                        vis[nx][ny] = true;
                        ++size;
                    }
                }
                areaSize.add(size);
                answer[1] = Math.max(answer[1], size);
            }
        }
        answer[0] = num;
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}