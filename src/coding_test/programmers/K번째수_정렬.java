package coding_test.programmers;

import java.util.*;

public class K번째수_정렬 {

    class Solution {
        public int[] solution(int[] array, int[][] commands) {
            int commandsLength = commands.length;
            int[] answer = new int[commandsLength];

            for (int i=0; i < commandsLength; i++) {
                int target;
                target = findNumber(array, commands[i][0], commands[i][1], commands[i][2]);
                answer[i] = target;

            }
            return answer;
        }

        private int findNumber(int[] array, int start, int end, int targetIndex) {
            int targetNumber = 0;
            int[] slicedArray;

            slicedArray = Arrays.copyOfRange(array, start-1, end);
            Arrays.sort(slicedArray);

            return slicedArray[targetIndex - 1];
        }
    }
}
