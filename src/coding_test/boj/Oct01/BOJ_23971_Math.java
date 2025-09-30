package coding_test.boj.Oct01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_23971_Math {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 세로로 앉을 수 있는 사람 수를 계산
        int rows = (H - 1) / (N + 1) + 1;
        
        // 가로로 앉을 수 있는 사람 수를 계산
        int cols = (W - 1) / (M + 1) + 1;

        System.out.println(rows * cols);
    }
}
