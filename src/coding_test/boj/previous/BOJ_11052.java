package coding_test.boj.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class BOJ_11052 {
    public static void main(String[] args) throws IOException {
        // Fast I/O 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int cardNumber = Integer.parseInt(br.readLine());
        
        // 한 줄에서 모든 카드 가격 읽기
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] card = new int[cardNumber + 1];
        for (int i = 1; i <= cardNumber; i++) {
            card[i] = Integer.parseInt(st.nextToken());
        }

        // DP 배열 - 메모리 최적화
        int[] dp = new int[cardNumber + 1];

        // DP 점화식 - 이미 최적화됨 O(N^2)
        for (int i = 1; i <= cardNumber; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] = Math.max(dp[i], dp[i - j] + card[j]);
            }
        }

        System.out.println(dp[cardNumber]);
        br.close();
    }
}
