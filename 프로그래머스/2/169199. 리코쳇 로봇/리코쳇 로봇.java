import java.util.*;
import java.awt.Point;

class Solution {
    
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};
    static int n, m, rX, rY, gX, gY;
    static int[][] dist;
    static char[][] area;
    
    static int isEnd(int x, int y) {
        if(x < 0 || x >= n || y < 0 || y >= m) return 1;
        if(area[x][y] == 'D') return 2;
        return 0;
    }
    
    static Point move(int x, int y, int dir) {
        int nx = x;
        int ny = y;
        while(isEnd(nx + dx[dir], ny + dy[dir]) == 0) {
            nx += dx[dir];
            ny += dy[dir];
        }
        // D에 도달하거나 범위 벗어나기 직전에 멈춘 상황
        return new Point(nx, ny);
    }
    
    public int solution(String[] board) {
        n = board.length;
        m = board[0].length();
        area = new char[n][m];
        dist = new int[n][m];
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < m; ++j) {
                area[i][j] = board[i].charAt(j);
                if(area[i][j] == 'R') {
                    rX = i; rY = j;
                    continue;
                } else if(area[i][j] == 'G') {
                    gX = i; gY = j;
                }
                dist[i][j] = (int)1e9;
            }
        }
        Deque<Point> q = new ArrayDeque<>();
        q.add(new Point(rX, rY));
        while(!q.isEmpty()) {
            Point cur = q.poll();
            for(int dir = 0; dir < 4; ++dir) {
                Point nxt = move(cur.x, cur.y, dir);
                //위치 변동 없을 경우 이미 막힌 케이스
                if(nxt.x == cur.x && nxt.y == cur.y) continue;
                if(dist[cur.x][cur.y] + 1 >= dist[nxt.x][nxt.y]) continue;
                dist[nxt.x][nxt.y] = dist[cur.x][cur.y] + 1;
                q.add(nxt);
            }
        }
        if(dist[gX][gY] == (int)1e9) return -1;
        return dist[gX][gY];
    }
}