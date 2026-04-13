import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int n, answer;
    static Egg[] eggs;
    static class Egg {
        int s, w;
        Egg(int s, int w) {
            this.s = s;
            this.w = w;
        }
    }

    static void calculate() {
        int count = 0;
        for (int i = 0; i < n; ++i) {
            if(eggs[i].s <= 0) ++count;
        }
        answer = Math.max(answer, count);
    }

    static void process(int index) {
        calculate();
        if(index == n) {
            return;
        }
        if(eggs[index].s <= 0) {
            process(index + 1);
            return;
        }
        for (int i = 0; i < n; ++i) {
            //같은 계란이거나 내구도가 0보다 작거나 같은(깨진) 경우
            if(index == i || eggs[i].s <= 0) continue;
            eggs[i].s -= eggs[index].w;
            eggs[index].s -= eggs[i].w;
            //다음 인덱스의 계란과, 앞선 계란과 하나씩 부딪쳐본다
            process(index + 1);
            eggs[i].s += eggs[index].w;
            eggs[index].s += eggs[i].w;
        }
    }

    static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        eggs = new Egg[n];
        for(int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            eggs[i] = new Egg(Integer.parseInt(st.nextToken()),
                              Integer.parseInt(st.nextToken()));
        }
        answer = 0;
    }

    static void solution() {
        process(0);
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}