package coding_test.programmers;

public class 피로도_완전탐색 {
    class Solution {
        private int maxCount = 0;

        public int solution(int k, int[][] dungeons) {
            boolean[] visited = new boolean[dungeons.length];
            permutation(k, dungeons, visited, 0);
            return maxCount;
        }

        /**
         * 순열을 이용한 완전탐색
         * @param currentFatigue 현재 피로도
         * @param dungeons 던전 배열 [[최소 필요 피로도, 소모 피로도], ...]
         * @param visited 방문 여부 체크
         * @param count 현재까지 탐험한 던전 개수
         */
        private void permutation(int currentFatigue, int[][] dungeons,
                                boolean[] visited, int count) {
            // 최대 던전 개수 갱신
            maxCount = Math.max(maxCount, count);

            // 모든 던전을 순회하며 탐험 시도
            for (int i = 0; i < dungeons.length; i++) {
                // 이미 방문했거나 피로도가 부족하면 스킵
                if (visited[i] || currentFatigue < dungeons[i][0]) {
                    continue;
                }

                // 던전 탐험
                visited[i] = true;
                permutation(currentFatigue - dungeons[i][1], dungeons, visited, count + 1);
                visited[i] = false; // 백트래킹
            }
        }
    }

    // 테스트 코드
    public static void main(String[] args) {
        피로도_완전탐색 main = new 피로도_완전탐색();
        Solution sol = main.new Solution();

        // 테스트 케이스 1
        int k = 80;
        int[][] dungeons = {{80, 20}, {50, 40}, {30, 10}};
        System.out.println("결과: " + sol.solution(k, dungeons));
    }
}
