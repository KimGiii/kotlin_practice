package coding_test.leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.System.out;

class leet_22 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        out.println(generateParenthesis(n));
    }

    public static List<String> generateParenthesis(int n) {
        String[] parentheses;

        /*
         * 괄호가 생성되려면 무조건 여는 괄호가 먼저 나와야 함
         * 괄호 체크 조건
         * 여는 괄호를 버퍼에 저장하고,
         * 닫는 괄호가 나올때마다 여는 괄호 pop
         * 끝까지 확인했을때 여는 괄호가 남아있으면 안됨
         */

        while (true) {
            if (n == 1) {
                parentheses = new String[]{"()"};
                break;
            } else if (n == 2) {
                parentheses = new String[]{"()()", "(())"};
                break;
            } else if (n == 3) {
                parentheses = new String[]{"((()))", "(()())", "(())()", "()(())", "()()()"};
                break;
            }
        }

        return Arrays.asList(parentheses);
    }
}
