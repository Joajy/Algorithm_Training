import java.util.*;

class Solution {

    static boolean[] vis;
    static int[] mul;
    
    public int solution(int[] cards) {
        int answer = 0;
        int n = cards.length;
        int[] c = new int[n + 1];
        for(int i = 0; i < n; ++i) {
            c[i + 1] = cards[i];
        }
        vis = new boolean[n + 1];
        mul = new int[n + 1];
        for(int i = 1; i <= n; ++i) {
            int card = c[i];
            if(vis[card]) continue;
            vis[card] = true;
            Deque<Integer> q = new ArrayDeque<>();
            q.add(card);
            while(!q.isEmpty()) {
                int cur = q.poll();
                ++mul[card];
                int nxt = c[cur];
                if(vis[nxt]) break;
                vis[nxt] = true;
                q.add(nxt);
            }
        }
        for(int i = 1; i <= n; ++i) {
            for(int j = i + 1; j <= n; ++j) {
                answer = Math.max(answer, mul[i] * mul[j]);
            }
        }
        return answer;
    }
}