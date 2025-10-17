package coding_test.kakao;

import java.util.*;

public class 카카오_1 {
    class Solution {
        class Word {
            String text;
            int start;
            int end;

            Word(String text, int start, int end) {
                this.text = text;
                this.start = start;
                this.end = end;
            }
        }

        public int solution(String message, int[][] spoiler_ranges) {
            // 왼쪽 -> 오른쪽 순서로 스포 방지 구간을 하나씩 클릭해 공개되는 단어 중 중요 단어가 몇개인지 확인

            /*
            단어 및 중요 단어 규칙
                1. 단어는 공백으로 구분, 알파벳 소문자와 숫자로만 구성된 연속된 문자열
                2. 단어를 구성하는 문자들의 인덱스 중 하나 이상이 스포 방지 구간에 포함될 경우, 해당 단어는 스포일러 방지 단어로 간주
                3. 한 단어가 여러 개의 스포 방지 구간어 걸쳐 있을 수 있으며, 하나의 스포 방지 구간에 여러 단어 포함 가능
                4. 스포 방지 구간을 클릭해 모든 문자가 공개되었을 때, 그 단어가 아래 조건을 모두 만족해야함
                    1. 스포 방지 단어여야 함
                    2. 메시지의 스포 방지 구간이 아닌 구간에 등장한 적이 없어야함
                    3. 이전에 공개된 스포 방지 단어와 중복되지 않아야 함
                    4. 여러 단어가 동시에 공개된 경우, 왼쪽부터 순서대로 하나씩 중요 단어인지 판단

             제한 사항
                1. 1 <= message <= 20000
                    1. message는 알파벳 소문자, 숫자, 공백으로 이루어져 있음
                    2. message는 하나 이상의 단어로 구성된 문자열
                    3. 공백은 연속해서 등장하지 않음
                2. 1 <= spoiler_ranges <= 1000
                    1. spoiler_range[i]는 [start, end] 형태로 스포 방지 적용 구간을 나타낸다. 이때 start와 end는 문자 인덱스
                    2. 0 <= start <= end < message.length()
                    3. 모든 구간은 서로 겹치지 않으며, start 기준으로 오름차순 정렬되어 주어짐
             */

            // 1. 단어 파싱
            List<Word> words = new ArrayList<>();
            int startIdx = 0;
            for (int i = 0; i < message.length(); i++) {
                if (message.charAt(i) == ' ') {
                    if (startIdx < i) {
                        words.add(new Word(message.substring(startIdx, i), startIdx, i - 1));
                    }
                    startIdx = i + 1;
                }
            }
            if (startIdx < message.length()) {
                words.add(new Word(message.substring(startIdx), startIdx, message.length() - 1));
            }

            // 2. 전처리: coverIdx 생성 및 단어 그룹화
            int n = message.length();
            int[] coverIdx = new int[n];
            Arrays.fill(coverIdx, -1);
            for (int r = 0; r < spoiler_ranges.length; r++) {
                int start = spoiler_ranges[r][0];
                int end = spoiler_ranges[r][1];
                for (int p = start; p <= end; p++) {
                    coverIdx[p] = r; // 마지막 구간 인덱스로 덮어쓰기
                }
            }

            List<List<Word>> buckets = new ArrayList<>(spoiler_ranges.length);
            for (int i = 0; i < spoiler_ranges.length; i++) {
                buckets.add(new ArrayList<>());
            }
            Set<String> plainWords = new HashSet<>();

            for (Word word : words) {
                int lastRevealIndex = -1;
                for (int p = word.start; p <= word.end; p++) {
                    lastRevealIndex = Math.max(lastRevealIndex, coverIdx[p]);
                }

                if (lastRevealIndex < 0) {
                    plainWords.add(word.text); // 스포일러 단어가 아님
                } else {
                    buckets.get(lastRevealIndex).add(word); // 최종 공개 시점에 따라 버킷에 추가
                }
            }

            // 3. 순차적 판별
            int answer = 0;
            Set<String> foundImportantWords = new HashSet<>();

            for (int i = 0; i < spoiler_ranges.length; i++) {
                List<Word> wordsToJudge = buckets.get(i);
                if (wordsToJudge.isEmpty()) {
                    continue;
                }

                // 중요: 좌->우 판정을 위해 버킷 내부를 단어 시작 순으로 정렬
                wordsToJudge.sort(Comparator.comparingInt(w -> w.start));

                for (Word word : wordsToJudge) {
                    if (plainWords.contains(word.text)) {
                        continue;
                    }
                    if (foundImportantWords.contains(word.text)) {
                        continue;
                    }
                    answer++;
                    foundImportantWords.add(word.text);
                }
            }

            return answer;
        }
    }
}
