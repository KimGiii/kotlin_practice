package coding_test.boj.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class BOJ_11650 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 데이터: 첫 번째 줄의 n값
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];

        // 각 좌표 읽고 배열에 저장 - StringTokenizer로 최적화
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        // Arrays.sort를 사용하여 정렬
        Arrays.sort(arr, (a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]); // x좌표가 같을 경우 y좌표로 정렬
            }
            return Integer.compare(a[0], b[0]); // x좌표 기준 정렬
        });

        // 결과 출력 - 상수 최적화
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(arr[i][0]).append(' ').append(arr[i][1]).append('\n');
        }
        System.out.print(sb);
        br.close();
    }
}
