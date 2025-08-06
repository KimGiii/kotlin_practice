package coding_test.programmers;

public class pro_12951 {
    public static void main(String[] args) {
        pro_12951 test = new pro_12951();
        String result = test.solution("hello world this is a test");
        String result2 = test.solution2("hello world this is a test");
        System.out.println(result); // 출력: "Hello World This Is A Test"
        System.out.println(result2);
    }

    public String solution(String s) {
        StringBuilder result = new StringBuilder(); // 수정 가능한 문자열을 위한 StringBuilder 사용
        boolean capitalizeNext = true; // 다음 문자를 대문자로 변환할지 여부를 나타냄

        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) { // 현재 문자가 공백인지 확인
                result.append(c);
                capitalizeNext = true; // 공백 이후에는 대문자로 시작해야 함
            } else if (capitalizeNext) {
                result.append(Character.toUpperCase(c));
                capitalizeNext = false; // 대문자로 변환한 이후에는 소문자로 유지
            } else {
                result.append(Character.toLowerCase(c));
            }
        }

        return result.toString(); // 최종 결과 문자열 반환
    }

    public String solution2(String s) {
        String answer = "";
        String[] sp = s.toLowerCase().split("");
        boolean capitalizeNext = true;

        for (String ss : sp) {
            answer += capitalizeNext ? ss.toUpperCase() : ss;
            capitalizeNext = ss.equals(" ") ? true : false;
        }

        return answer;
    }
}
