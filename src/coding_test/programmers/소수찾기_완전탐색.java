package coding_test.programmers;

import java.util.*;

public class 소수찾기_완전탐색 {
    class Solution {
        public int solution(String numbers) {
            Set<Integer> numberSet = new HashSet<>();
            char[] chars = numbers.toCharArray();
            boolean[] visited = new boolean[chars.length];

            permutation(chars, visited, "", numberSet);

            return (int) numberSet.stream()
                    .filter(this::isPrime)
                    .count();
        }

        private void permutation(char[] chars, boolean[] visited, String current, Set<Integer> numberSet) {
            if (!current.isEmpty()) {
                numberSet.add(Integer.parseInt(current));
            }

            for (int i = 0; i < chars.length; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    permutation(chars, visited, current + chars[i], numberSet);
                    visited[i] = false;
                }
            }
        }

        private boolean isPrime(int n) {
            if (n < 2) return false;
            if (n == 2) return true;
            if (n % 2 == 0) return false;
            for (int i = 3; i * i <= n; i += 2) {
                if (n % i == 0) return false;
            }
            return true;
        }
    }
}
