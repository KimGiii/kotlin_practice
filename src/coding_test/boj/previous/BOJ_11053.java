package coding_test.boj.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class BOJ_11053 {
    // 총 시간복잡도 O(NlogN) - 공간복잡도 최적화
    public static void main(String[] args) throws IOException {
        // Fast I/O 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
        // 한 줄에서 모든 수 읽기
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 메모리 최적화: ArrayList 대신 배열 사용
        int[] lis = new int[N];
        
        int result = findLISLength(A, lis);
        System.out.println(result);
        br.close();
    }

    private static int findLISLength(int[] A, int[] lis) {
        int length = 0;
        for (int num : A) {
            // 직접 이진 탐색 구현으로 상수 최적화
            int pos = binarySearch(lis, 0, length, num);

            if (pos == length) {
                lis[length++] = num; // 새로운 수열 확장
            } else {
                lis[pos] = num; // 기존 수 대체
            }
        }
        return length;
    }
    
    // 직접 구현한 lower_bound 이진 탐색
    private static int binarySearch(int[] arr, int left, int right, int target) {
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
