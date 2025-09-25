// https://www.acmicpc.net/problem/11728
// 두 정렬된 배열을 하나로 병합하는 문제 (O(N + M))
package coding_test.boj;

class BOJ_11728 {
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();

        int N = read(); // 첫 번째 배열의 크기
        int M = read(); // 두 번째 배열의 크기

        int[] arr1 = new int[N]; // 첫 번째 배열
        int[] arr2 = new int[M]; // 두 번째 배열

        // 첫 번째 배열 입력
        for (int i = 0; i < N; i++)
            arr1[i] = read();

        // 두 번째 배열 입력
        for (int i = 0; i < M; i++)
            arr2[i] = read();

        // 병합 로직 (투 포인터)
        int i = 0, j = 0;
        while (i < N && j < M) {
            if (arr1[i] < arr2[j]) {
                sb.append(arr1[i++]).append(' ');
            } else {
                sb.append(arr2[j++]).append(' ');
            }
        }

        // arr1의 남은 요소 추가
        while (i < N)
            sb.append(arr1[i++]).append(' ');

        // arr2의 남은 요소 추가
        while (j < M)
            sb.append(arr2[j++]).append(' ');

        // 결과 출력
        System.out.print(sb);
    }

    // 고속 입력 처리 함수
    static int read() throws Exception {
        int c, n = 0;
        boolean negative = false;

        // 공백/줄바꿈 스킵
        while ((c = System.in.read()) <= 32);

        // 음수 체크
        if (c == '-') {
            negative = true;
            c = System.in.read();
        }

        // 숫자 누적
        do {
            n = n * 10 + (c - '0');
        } while ((c = System.in.read()) >= '0' && c <= '9');

        return negative ? -n : n;
    }

}
