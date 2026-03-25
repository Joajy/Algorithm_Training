import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int n, m;
    static int[] crane, box;
    static boolean[] finish;

    static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        crane = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            crane[i] = Integer.parseInt(st.nextToken());
        }
        m = Integer.parseInt(br.readLine());
        box = new int[m];
        finish = new boolean[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; ++i) {
            box[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solution() {
        Arrays.sort(crane);
        Arrays.sort(box);
        //가장 큰 크레인으로 가장 큰 박스를 옮길 수 없다면 즉시 종료
        if(crane[n - 1] < box[m - 1]) {
            System.out.println(-1);
            return;
        }
        int count = 0;
        for (int i = 0; i <= 10000; ++i) {
            //전부 옮겼을 경우에는 해당 일자 출력 후 즉시 종료
            if(count == box.length) {
                System.out.println(i);
                return;
            }
            int index = n - 1;
            for (int j = m - 1; j >= 0; --j) {
                //이미 옮겼거나
                //해당 크레인으로 박스 못 옮길 경우에는 더 가벼운 박스 찾으러 이동
                if(finish[j]) continue;
                if(crane[index] < box[j]) continue;
                finish[j] = true;
                ++count;
                if(--index < 0) break;
            }
        }
        //박스 다 못 옮길 경우 -1 출력
        System.out.println(-1);
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}