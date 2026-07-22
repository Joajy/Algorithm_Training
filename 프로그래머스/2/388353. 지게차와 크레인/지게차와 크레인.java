import java.util.*;

class Solution {
    
    static int n, m;
    static char[][] area;
    static boolean[][] vis;
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};
    static class Info {
        int x, y;
        Info(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    // 1. 알파벳 하나만 나온 경우 -> 최외곽
    static void doReqOne(char req) {
        boolean[][] visited = new boolean[n + 2][m + 2];
        boolean[][] add = new boolean[n + 2][m + 2];
        Deque<Info> q = new ArrayDeque<>();
        for(int i = 1; i <= n; ++i) {
            q.add(new Info(i, 0));
            q.add(new Info(i, m + 1));
        }
        for(int i = 1; i <= m; ++i) {
            q.add(new Info(0, i));
            q.add(new Info(n + 1, i));
        }
        while(!q.isEmpty()) {
            Info cur = q.poll();
            for(int dir = 0; dir < 4; ++dir) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if(nx < 1 || nx > n || ny < 1 || ny > m) continue;
                if(visited[nx][ny]) continue;
                if(!vis[nx][ny]){
                    if(area[nx][ny] == req) add[nx][ny] = true;
                    continue;
                }
                visited[nx][ny] = true;
                q.add(new Info(nx, ny));
            }
        }
        copy(add);
    }
    
    // 2. 알파벳 두개 나온 경우 -> 일대 모든 알파벳 제거
    static void doReqTwo(char req) {
        for(int i = 1; i <= n; ++i) {
            for(int j = 1; j <= m; ++j) {
                // if(vis[i][j]) continue;
                if(area[i][j] == req) vis[i][j] = true;
            }
        }
    }
    
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        input(storage);
        for(String req : requests) {
            if(req.length() == 1) {
                doReqOne(req.charAt(0));
            } else {
                doReqTwo(req.charAt(0));
            }
        }
        for(int i = 1; i <= n; ++i) {
            for(int j = 1; j <= m; ++j) {
                if(vis[i][j]) continue;
                ++answer;
            }
        }
        return answer;
    }
    
    static void copy(boolean[][] visited) {
        for(int i = 1; i <= n; ++i) {
            for(int j = 1; j <= m; ++j) {
                if(visited[i][j]) vis[i][j] = visited[i][j];
            }
        }
    }

    static void input(String[] storage) {
        n = storage.length;
        m = storage[0].length();
        area = new char[n + 2][m + 2];
        vis = new boolean[n + 2][m + 2];
        for(int i = 1; i <= n; ++i) {
            for(int j = 1; j <= m; ++j) {
                area[i][j] = storage[i - 1].charAt(j - 1);
            }
        }
    }
}