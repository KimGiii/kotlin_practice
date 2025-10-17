package coding_test.boj.previous;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.ArrayDeque;

public class BOJ_9012 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String parenthesis = br.readLine();

            if (isValidParenthesis(parenthesis)) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }

        System.out.println(sb);
    }

    private static boolean isValidParenthesis(String parenthesis) {
        Deque<Character> stack = new ArrayDeque<>();
        
        for (int j = 0; j < parenthesis.length(); j++) {
            char c = parenthesis.charAt(j);
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty() || stack.pop() != '(') { 
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
