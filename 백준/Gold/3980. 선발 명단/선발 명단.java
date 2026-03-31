import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int answer;
    static int[][] stat;
    static boolean[] vis;

    static void input() throws IOException {
        stat = new int[11][11];
        for(int i = 0; i < 11; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 11; ++j) {
                stat[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        vis = new boolean[11];
        answer = 0;
    }

    static void setPosition(int index, int total) {
        if(index == 11) {
            answer = Math.max(answer, total);
            return;
        }
        for (int i = 0; i < 11; ++i) {
            if(stat[index][i] == 0 || vis[i]) continue;
            vis[i] = true;
            setPosition(index + 1, total + stat[index][i]);
            vis[i] = false;
        }
    }

    static void solution() {
        setPosition(0, 0);
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        int c = Integer.parseInt(br.readLine());
        for(int i = 0; i < c; ++i) {
            input();
            solution();
        }
    }
}