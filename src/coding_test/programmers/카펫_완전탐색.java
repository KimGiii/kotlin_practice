package coding_test.programmers;

public class 카펫_완전탐색 {
    class Solution {
        public int[] solution(int brown, int yellow) {
            int total = brown + yellow; // 전체 격자 개수

            // 세로 길이는 최소 3부터 시작 (테두리 2칸 + 내부 1칸)
            for (int height = 3; height <= total / height; height++) {
                // 전체 격자 개수가 height로 나누어떨어지는 경우만 검사
                if (total % height == 0) {
                    int width = total / height;

                    // 가로가 세로보다 크거나 같아야 함
                    if (width >= height) {
                        // 내부 노란색 영역 크기 확인
                        int innerWidth = width - 2;
                        int innerHeight = height - 2;

                        if (innerWidth * innerHeight == yellow) {
                            return new int[]{width, height};
                        }
                    }
                }
            }

            return new int[]{};
        }
    }
}
