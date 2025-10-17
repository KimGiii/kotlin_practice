package coding_test.kakao;

import java.util.*;

public class 카카오_2 {
    class Solution {
        private static int gcd(int a, int b) {
            while (b != 0) {
                int tmp = a % b;
                a = b;
                b = tmp;
            }
            return Math.abs(a);
        }

        private static int lcm(int a, int b) {
            return a / gcd(a, b) * b;
        }

        public int solution(int[][] signals) {
            /*
            도로에 n개의 신호등이 존재
            모든 신호등은 초록불 -> 노란불 -> 빨간불 순서로 반복되며, 각 신호의 지속시간은 신호등마다 다름
            시간은 1초부터 시작하며, 각 신호등은 처음에는 초록불로 시작
            모든 신호등이 모두 노란불이 되면 정전이 발생
            이때, 모든 신호등이 노란불이 되는 가장 빠른 시간을 return하도록

            제한사항
            1. 2 <= signals.length <= 5
                1. signals의 원소는 [G, Y, R] 형태의 길이가 3인 정수 배열, 순서대로 초록불, 노란불, 빨간불의 지속 시간을 의미
                2. 1 <= G, Y, R <= 18
                3. 3 <= G+Y+R <= 20
             */

            int n = signals.length;
            int[] cycleDurations = new int[n]; // 각 신호등의 전체 주기
            List<boolean[]> yellowPhases = new ArrayList<>(); // 노란불 잔여류 집합

            for (int i = 0; i < n; i++) {
                int green = signals[i][0];
                int yellow = signals[i][1];
                int red = signals[i][2];
                int cycle = green + yellow + red;
                cycleDurations[i] = cycle;

                boolean[] isYellow = new boolean[cycle];
                for (int second = green; second < green + yellow; second++) {
                    isYellow[second] = true;
                }
                yellowPhases.add(isYellow);
            }

            // 첫 신호등 기준 초기 허용 시간(잔여류)
            int currentMod = cycleDurations[0];
            BitSet validTimes = new BitSet(currentMod);
            boolean[] firstYellow = yellowPhases.get(0);
            for (int r = 0; r < currentMod; r++) {
                if (firstYellow[r]) validTimes.set(r);
            }

            // 나머지 신호등과 교집합 계산
            for (int i = 1; i < n; i++) {
                int nextCycle = cycleDurations[i];
                boolean[] nextYellow = yellowPhases.get(i);
                int combinedMod = lcm(currentMod, nextCycle);
                BitSet newValidTimes = new BitSet(combinedMod);

                for (int prevResidue = validTimes.nextSetBit(0);
                     prevResidue >= 0;
                     prevResidue = validTimes.nextSetBit(prevResidue + 1)) {

                    for (int k = 0; k < combinedMod / currentMod; k++) {
                        int candidateTime = prevResidue + k * currentMod;
                        if (nextYellow[candidateTime % nextCycle]) {
                            newValidTimes.set(candidateTime);
                        }
                    }
                }

                validTimes = newValidTimes;
                currentMod = combinedMod;
                if (validTimes.isEmpty()) return -1; // 교집합 없음
            }

            int earliestResidue = validTimes.nextSetBit(0);
            return (earliestResidue == -1) ? -1 : earliestResidue + 1; // 시간은 1초부터 시작
        }
    }
}
