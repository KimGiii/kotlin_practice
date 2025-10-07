package coding_test.programmers;

import java.util.Arrays;

public class K번째수_정렬 {

    class Solution {
        public int[] solution(int[] array, int[][] commands) {
            int commandsLength = commands.length;
            int[] answer = new int[commandsLength];

            for (int i = 0; i < commandsLength; i++) {
                int target;
                target = findNumber(array, commands[i][0], commands[i][1], commands[i][2]);
                answer[i] = target;
            }
            return answer;
        }

        private int findNumber(int[] array, int start, int end, int targetIndex) {
            int targetNumber = 0;
            int[] slicedArray;

            slicedArray = Arrays.copyOfRange(array, start - 1, end);
            Arrays.sort(slicedArray);

            return slicedArray[targetIndex - 1];
        }

        // 선택 알고리즘을 사용해 K번째 수만 찾는 방법
        public int[] solution2(int[] array, int[][] commands) {
            int[] answer = new int[commands.length];

            for (int i = 0; i < commands.length; i++) {
                int[] command = commands[i];
                int start = command[0] - 1;
                int end = command[1];
                int k = command[2] - 1;

                int[] slicedArray = Arrays.copyOfRange(array, start, end);
                answer[i] = quickSelect(slicedArray, 0, slicedArray.length - 1, k);
            }

            return answer;
        }

        private int quickSelect(int[] arr, int left, int right, int k) {
            while (left <= right) {
                int pivotIndex = partition(arr, left, right);
                if (pivotIndex == k) {
                    return arr[k];
                } else if (pivotIndex > k) {
                    right = pivotIndex - 1;
                } else {
                    left = pivotIndex + 1;
                }
            }
            return -1;
        }

        private int partition(int[] arr, int left, int right) {
            int pivotValue = arr[right];
            int i = left;

            for (int j = left; j < right; j++) {
                if (arr[j] < pivotValue) {
                    swap(arr, i, j);
                    i++;
                }
            }

            swap(arr, i, right);
            return i;
        }

        private void swap(int[] arr, int a, int b) {
            int temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }
    }
}
