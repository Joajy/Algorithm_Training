class Solution {
    
    static int n, m, answer;
    static int[][] board, end;
    
    static boolean isSame() {
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < m; ++j) {
                if(board[i][j] != end[i][j]) return false;
            }            
        }
        return true;
    }
    
    static void rec(int r, int c, int count) {
        // 더 넘어가는 경우 필요없다
        if(count >= answer) return;
        // 가장 끝 행과 끝 열에 도달한 경우
        if(r == n && c == m) {
            if(!isSame()) return;
            answer = Math.min(answer, count);
            return;
        }
        if(r < n) {
            // 현재 행을 뒤집지 않는 경우
            rec(r + 1, c, count);
            // 현재 행을 뒤집는 경우
            for(int i = 0; i < m; ++i) {
                board[r][i] = 1 - board[r][i];
            }
            rec(r + 1, c, count + 1);
            // 복구
            for(int i = 0; i < m; ++i) {
                board[r][i] = 1 - board[r][i];
            }
        }
        else {
            // 모든 행을 결정했으므로 이제 열을 결정
            // 현재 열을 뒤집지 않는 경우
            rec(r, c + 1, count);
            // 현재 열을 뒤집는 경우
            for(int i = 0; i < n; ++i) {
                board[i][c] = 1 - board[i][c];
            }
            rec(r, c + 1, count + 1);
            // 복구
            for(int i = 0; i < n; ++i) {
                board[i][c] = 1 - board[i][c];
            }
        }
    }
    
    public int solution(int[][] beginning, int[][] target) {
        answer = (int) 1e9;
        n = target.length;
        m = target[0].length;
        board = beginning;
        end = target;
        rec(0, 0, 0);
        if(answer == (int)1e9) return -1;
        return answer;
    }
}