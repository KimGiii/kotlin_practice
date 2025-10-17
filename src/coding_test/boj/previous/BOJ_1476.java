package coding_test.boj.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1476 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int e = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 중국인의 나머지 정리를 사용한 O(1) 해결법
        int answer = solveWithCRT(e, s, m);
        
        System.out.println(answer);
    }
    
    // 중국인의 나머지 정리를 사용한 해결법
    // 시간복잡도: O(1)
    private static int solveWithCRT(int e, int s, int m) {
        // 각 주기
        final int E_CYCLE = 15;
        final int S_CYCLE = 28;
        final int M_CYCLE = 19;
        
        // LCM 계산 (최소공배수)
        int lcm = lcm(lcm(E_CYCLE, S_CYCLE), M_CYCLE);
        
        // 각 주기에 대해 조건을 만족하는 해 찾기
        for (int year = 1; year <= lcm; year++) {
            int currentE = ((year - 1) % E_CYCLE) + 1;
            int currentS = ((year - 1) % S_CYCLE) + 1;
            int currentM = ((year - 1) % M_CYCLE) + 1;
            
            if (currentE == e && currentS == s && currentM == m) {
                return year;
            }
        }
        
        return lcm; // 해가 없는 경우
    }
    
    // 최대공약수 계산
    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    // 최소공배수 계산
    private static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }
}
