package coding_test.programmers;

import java.util.Arrays;
import java.util.Comparator;

public class 가장큰수_정렬 {
    class Solution {
        public String solution(int[] numbers) {
            // 1. int[]를 String[]으로 변환
            String[] strNumbers = new String[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                strNumbers[i] = String.valueOf(numbers[i]);
            }

            Arrays.sort(strNumbers, new Comparator<String>() {
                @Override
                public int compare(String a, String b) {
                    return (b + a).compareTo(a + b);
                }
            });
            
            // 람다 표현식
            // Arrays.sort(strNumbers, (a, b) -> (b + a).compareTo(a + b));

            // 3. 정렬 후 첫 번째가 "0"이면 나머지도 모두 "0"
            if (strNumbers[0].equals("0")) {
                return "0";
            }

            // 4. 정렬된 문자열들을 합쳐서 결과 반환
            StringBuilder answer = new StringBuilder();
            for (String s : strNumbers) {
                answer.append(s);
            }

            return answer.toString();
        }
    }
}
