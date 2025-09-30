package coding_test.boj.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11004 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        quickSelect(arr, 0, n - 1, k - 1);
        System.out.println(arr[k - 1]);
    }

    private static void quickSelect(int[] arr, int start, int end, int k) {
        if (start < end) {
            int pivot = partition(arr, start, end);
            if (pivot == k) {
                return;
            } else if (pivot > k) {
                quickSelect(arr, start, pivot - 1, k);
            } else {
                quickSelect(arr, pivot + 1, end, k);
            }
        }
    }

    private static int partition(int[] arr, int start, int end) {
        int mid = (start + end) / 2;
        swap(arr, start, mid);

        int pivot = arr[start];
        int i = start + 1;
        int j = end;

        while (i <= j) {
            while (i <= end && arr[i] <= pivot) i++;
            while (j > start && arr[j] > pivot) j--;
            if (i < j) {
                swap(arr, i, j);
            }
        }

        swap(arr, start, j);
        return j;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
