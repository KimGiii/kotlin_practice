package coding_test;

class pro_12941 {
    public static void main(String[] args) {
        pro_12941 test = new pro_12941();
        int[] A = {1, 2, 3, 4};
        int[] B = {2, 4, 6, 8};
        System.out.println(test.solution(A, B));
    }

    public int solution(int[] A, int[] B) {
        int answer = 0;

        // A와 B를 정렬
        // Arrays.sort(A);
        quickSort(A, 0, A.length - 1);
        // Arrays.sort(B);
        quickSort(B, 0, B.length - 1);

        // A의 최소와 B의 최대를 곱해서 더하는게 결과가 최소
        for (int i = 0; i < A.length; i++) {
            answer += A[i] * B[B.length - i - 1];
        }

        return answer;
    }

    // Arrays.sort 사용안하고 quick sort 직접 구현 시도
    public static void quickSort(int[] arr, int left, int right) {
        int i, j, pivot, temp;

        if (left < right) {
            i = left;
            j = right;
            pivot = arr[left];

            while (i < j) {
                while (arr[j] > pivot) j--;
                while (i < j && arr[i] < pivot) i++;

                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
            arr[left] = arr[i];
            arr[i] = pivot;

            quickSort(arr, left, i - 1);
            quickSort(arr, i + 1, right);
        }
    }
}
