package coding_test.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_10799 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        int answer = 0;
        int open = 0;                 // current number of open rods

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    answer += open;
                    i++;
                } else {
                    open++;
                }
            } else {
                open--;
                answer++;
            }
        }

        System.out.println(answer);
    }

}
