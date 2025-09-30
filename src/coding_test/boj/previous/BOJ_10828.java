package coding_test.boj.previous;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class BOJ_10828 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int[] stack = new int[N];
        int top = -1;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            if (command.equals("push")) {
                int num = Integer.parseInt(st.nextToken());
                stack[++top] = num;
            } else if (command.equals("pop")) {
                sb.append(top == -1 ? -1 : stack[top--]).append('\n');
            } else if (command.equals("size")) {
                sb.append(top + 1).append('\n');
            } else if (command.equals("empty")) {
                sb.append(top == -1 ? 1 : 0).append('\n');
            } else if (command.equals("top")) {
                sb.append(top == -1 ? -1 : stack[top]).append('\n');
            }
        }
        System.out.print(sb);
    }
}
