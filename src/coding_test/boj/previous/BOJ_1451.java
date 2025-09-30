package coding_test.boj.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1451 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = line.charAt(j) - '0';
            }
        }

        // 누적 합 배열 생성
        long[][] prefixSum = new long[N+1][M+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                prefixSum[i][j] = prefixSum[i-1][j] + prefixSum[i][j-1] - prefixSum[i-1][j-1] + arr[i-1][j-1];
            }
        }
        
        long[] maxProduct = {0};
        
        // 1. 가로로 3등분 (||| 모양)
        for (int j = 0; j < M - 2; j++) {
            for (int k = j + 1; k < M - 1; k++) {
                long sum1 = getRectangleSum(prefixSum, 0, 0, N-1, j);
                long sum2 = getRectangleSum(prefixSum, 0, j+1, N-1, k);
                long sum3 = getRectangleSum(prefixSum, 0, k+1, N-1, M-1);
                
                updateMaxProduct(sum1, sum2, sum3, maxProduct);
            }
        }
        
        // 2. 세로로 3등분 (= 모양)
        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                long sum1 = getRectangleSum(prefixSum, 0, 0, i, M-1);
                long sum2 = getRectangleSum(prefixSum, i+1, 0, j, M-1);
                long sum3 = getRectangleSum(prefixSum, j+1, 0, N-1, M-1);
                
                updateMaxProduct(sum1, sum2, sum3, maxProduct);
            }
        }
        
        // 3. ㅗ 모양 분할
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M - 1; j++) {
                long sum1 = getRectangleSum(prefixSum, 0, 0, i, j);
                long sum2 = getRectangleSum(prefixSum, 0, j+1, i, M-1);
                long sum3 = getRectangleSum(prefixSum, i+1, 0, N-1, M-1);
                
                updateMaxProduct(sum1, sum2, sum3, maxProduct);
            }
        }
        
        // 4. ㅏ 모양 분할
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M - 1; j++) {
                long sum1 = getRectangleSum(prefixSum, 0, 0, i, j);
                long sum2 = getRectangleSum(prefixSum, i+1, 0, N-1, j);
                long sum3 = getRectangleSum(prefixSum, 0, j+1, N-1, M-1);
                
                updateMaxProduct(sum1, sum2, sum3, maxProduct);
            }
        }
        
        // 5. ㅓ 모양 분할
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M - 1; j++) {
                long sum1 = getRectangleSum(prefixSum, 0, 0, N-1, j);
                long sum2 = getRectangleSum(prefixSum, 0, j+1, i, M-1);
                long sum3 = getRectangleSum(prefixSum, i+1, j+1, N-1, M-1);
                
                updateMaxProduct(sum1, sum2, sum3, maxProduct);
            }
        }
        
        // 6. ㅜ 모양 분할
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M - 1; j++) {
                long sum1 = getRectangleSum(prefixSum, 0, 0, i, M-1);
                long sum2 = getRectangleSum(prefixSum, i+1, 0, N-1, j);
                long sum3 = getRectangleSum(prefixSum, i+1, j+1, N-1, M-1);
                
                updateMaxProduct(sum1, sum2, sum3, maxProduct);
            }
        }
        
        System.out.println(maxProduct[0]);
    }
    
    // 누적 합을 사용한 최적화된 직사각형 합 계산
    private static long getRectangleSum(long[][] prefixSum, int startRow, int startCol, int endRow, int endCol) {
        return prefixSum[endRow+1][endCol+1] - prefixSum[endRow+1][startCol] - prefixSum[startRow][endCol+1] + prefixSum[startRow][startCol];
    }
    
    // 공통 함수: 최대 곱 업데이트
    private static void updateMaxProduct(long sum1, long sum2, long sum3, long[] maxProduct) {
        long product = sum1 * sum2 * sum3;
        maxProduct[0] = Math.max(maxProduct[0], product);
    }
}
