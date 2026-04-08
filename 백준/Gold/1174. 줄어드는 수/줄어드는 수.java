import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static List<Long> answer;

    static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        answer = new ArrayList<>();
    }

    static void bt(int count, long number) {
        if(count > 10) return;
        for (long i = number % 10 - 1; i >= 0; --i) {
            answer.add(number * 10 + i);
            bt(count + 1, number * 10 + i);
        }
    }

    //1563 2056 2565 15684 16987
    static void solution() {
        for (long i = 0; i <= 9; ++i) {
            answer.add(i);
        }
        for (int i = 0; i <= 9; ++i) {
            bt(1, i);
        }
        Collections.sort(answer);
        if(answer.size() < n) System.out.println(-1);
        else System.out.println(answer.get(n - 1));
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}