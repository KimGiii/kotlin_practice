package coding_test.boj.previous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1107 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int targetChannel = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        int errorButtonNumber = Integer.parseInt(st.nextToken());
        
        // 고장난 버튼들을 저장할 배열
        boolean[] brokenButtons = new boolean[10];
        
        if (errorButtonNumber > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < errorButtonNumber; i++) {
                int errorButton = Integer.parseInt(st.nextToken());
                brokenButtons[errorButton] = true;
            }
        }

        // 시작 채널이 100이면 버튼을 누를 필요 없음
        if (targetChannel == 100) {
            System.out.println(0);
            return;
        }

        // 최소 버튼 클릭 수 계산
        int minClicks = solve(targetChannel, brokenButtons);
        System.out.println(minClicks);
    }

    private static int solve(int targetChannel, boolean[] brokenButtons) {
        // 1. 100에서 + 또는 - 버튼만으로 이동하는 경우
        int clicksFrom100 = Math.abs(targetChannel - 100);
        int minClicks = clicksFrom100;
        
        // 2. 숫자 버튼을 사용하는 경우 - 모든 가능한 채널 확인
        // 최대 6자리까지 가능하므로 0부터 999999까지 확인
        for (int channel = 0; channel <= 999999; channel++) {
            if (canMoveToChannel(channel, brokenButtons)) {
                int digitClicks = getDigitCount(channel);
                int moveClicks = Math.abs(targetChannel - channel);
                int totalClicks = digitClicks + moveClicks;
                
                minClicks = Math.min(minClicks, totalClicks);
            }
        }
        
        return minClicks;
    }
    
    // 해당 채널로 이동 가능한지 확인
    private static boolean canMoveToChannel(int channel, boolean[] brokenButtons) {
        if (channel == 0) {
            return !brokenButtons[0];
        }
        
        // 각 자릿수를 확인하여 고장난 버튼이 있는지 검사
        while (channel > 0) {
            int digit = channel % 10;
            if (brokenButtons[digit]) {
                return false;
            }
            channel /= 10;
        }
        return true;
    }
    
    // 숫자의 자릿수를 반환
    private static int getDigitCount(int number) {
        if (number == 0) {
            return 1;
        }
        
        // 로그를 사용한 자릿수 계산
        return (int) Math.log10(number) + 1;
    }
}
