package coding_test.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class BOJ_10814 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 나이 범위: 1 ~ 200
        // 각 나이에 해당하는 이름들을 저장할 StringBuilder 배열
        StringBuilder[] ageBuckets = new StringBuilder[201];

        // StringBuilder 배열 초기화
        for (int i = 0; i < ageBuckets.length; i++) {
            ageBuckets[i] = new StringBuilder();
        }

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            // 해당 나이의 버킷에 "나이 이름\n" 형태로 저장
            ageBuckets[age].append(age).append(' ').append(name).append('\n');
        }

        // 최종 출력을 위한 StringBuilder
        StringBuilder result = new StringBuilder();
        // 나이 1세부터 200세까지 순회하며 결과 취합
        for (StringBuilder bucket : ageBuckets) {
            result.append(bucket);
        }

        System.out.print(result);

        br.close();
    }
}
