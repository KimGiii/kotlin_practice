package coding_test.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10809 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String word = st.nextToken();
        int wordLength = word.length();
        int[] alphabet = new int[26];

        // 단어를 한 글자씩 확인
        // 첫번째 확인이면 단어의 index + 1을 alphabet에 저장
        // 이미 확인한 글자면 continue
        // 단어에 없는 글자면 전부 -1
        

    }
}
