import java.util.*;

class Solution {
    private static final int MAX_LENGTH = 12; // 최대 문자열 길이
    private static long[] ALPHABET = new long[MAX_LENGTH]; // 26진법 가중치 배열
    private static List<List<Long>> bannedSpells = new ArrayList<>(); // 금지된 문자열 숫자 값 저장

    public String solution(long n, String[] bans) {
        init();

        // 금지된 문자열을 숫자로 변환하여 저장
        for (String ban : bans) {
            int len = ban.length();
            long num = stringToNum(ban);
            bannedSpells.get(len).add(num);
        }

        // 금지된 숫자 목록 정렬
        for (int len = 1; len < MAX_LENGTH; len++) {
            Collections.sort(bannedSpells.get(len));
        }

        // n번째 유효한 문자열 찾기
        for (int len = 1; len < MAX_LENGTH; len++) {
            // 길이 len에 대한 가능한 모든 문자열의 수 계산
            long totalStrings = ALPHABET[len];
            // 금지된 문자열의 수
            long bannedCnt = bannedSpells.get(len).size();
            // 유효한 문자열의 수
            long validCnt = totalStrings - bannedCnt;

            // n번째 유효한 문자열이 현재 길이에서 존재하지 않는 경우, 다음 길이로 넘어감
            if (n > validCnt) {
                n -= validCnt; // 남은 유효한 문자열 수만큼 n 감소
                continue; // 다음 길이의 처리로 넘어감
            }


            long k = n;
            long prev = -1;
            long candidate = -1;

            // 금지된 숫자 사이의 간격을 통해 유효한 문자열 위치 찾기
            for (long bannedNum : bannedSpells.get(len)) {
                long gap = bannedNum - (prev + 1);
                if (k <= gap) {
                    candidate = prev + k;
                    break;
                }
                k -= gap;
                prev = bannedNum;
            }

            // 금지된 목록의 마지막 숫자 이후 위치 찾기
            if (candidate == -1) {
                candidate = prev + k;
            }

            return numToString(candidate, len);
        }

        return "";
    }

    private static void init() {
        ALPHABET[0] = 1;
        for (int len = 1; len < MAX_LENGTH; len++) {
            ALPHABET[len] = ALPHABET[len - 1] * 26; // 26진법 가중치 계산
        }

        // 금지된 문자열 저장을 위한 리스트 초기화
        for (int i = 0; i < MAX_LENGTH; i++) {
            bannedSpells.add(new ArrayList<>());
        }
    }

    private static long stringToNum(String s) {
        long num = 0;
        // 문자열을 숫자로 변환
        for (char c : s.toCharArray()) {
            num = num * 26 + (c - 'a');
        }
        return num;
    }

    private static String numToString(long num, int len) {
        char[] s = new char[len];
        // 숫자를 문자열로 변환
        for (int i = len - 1; i >= 0; i--) {
            s[i] = (char) ('a' + num % 26);
            num /= 26;
        }
        return new String(s);
    }
}
