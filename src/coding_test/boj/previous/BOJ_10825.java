package coding_test.boj.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10825 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        // 국어점수가 감소하는 순서로
        // 국어점수가 같으면 영어 점수가 증가하는 순서로
        // 국어 점수와 영어 점수가 같으면 수학 점수가 감소하는 순서로
        // 모든 점수가 같으면 이름이 사전순으로 증가하는 순서로

        String[][] students = new String[N][4];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            students[i][0] = st.nextToken(); // 이름
            students[i][1] = st.nextToken(); // 국어
            students[i][2] = st.nextToken(); // 영어
            students[i][3] = st.nextToken(); // 수학
        }

        for(int i = 0; i < N-1; i++) {
            if(Integer.parseInt(students[i][1]) < Integer.parseInt(students[i+1][1])) {
                swap(students, i, i+1);
            } else if (Integer.parseInt(students[i][1]) == Integer.parseInt(students[i+1][1])) {
                if(Integer.parseInt(students[i][2]) > Integer.parseInt(students[i+1][2])) {
                    swap(students, i, i+1);
                } else if(Integer.parseInt(students[i][2]) == Integer.parseInt(students[i+1][2])) {
                    if(Integer.parseInt(students[i][3]) < Integer.parseInt(students[i+1][3])) {
                        swap(students, i, i+1);
                    } else if(Integer.parseInt(students[i][3]) == Integer.parseInt(students[i+1][3])) {
                        // 이름 사전순 정렬
                        if(students[i][0].compareTo(students[i+1][0]) > 0) {
                            swap(students, i, i+1);
                        }
                    }
                }
            }
        }

        for(int i = 0; i < N; i++) {
            System.out.println(students[i][0]);
        }
    }

    public static void swap(String[][] students, int i, int j) {
        String[] temp = students[i];
        students[i] = students[j];
        students[j] = temp;
    }
}
