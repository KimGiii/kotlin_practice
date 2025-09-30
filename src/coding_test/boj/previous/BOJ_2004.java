package coding_test.boj.previous;

import java.util.Scanner;

class BOJ_2004 {

    public static void main(String[] args) {
        // 정답의 범위는 0 ~ 10임
        // 조합 결과의 0의 개수 -> 10의 제곱수 개수 찾기
        // 2와 5의 개수를 찾고 최소가 정답
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int countTwo = countPrime(n, 2) - countPrime(m, 2) - countPrime(n - m, 2);
        int countFive = countPrime(n, 5) - countPrime(m, 5) - countPrime(n - m, 5);

        System.out.println(Math.min(countTwo, countFive));
    }

    static int countPrime(int n, int prime) {
        int count = 0;
        while (n >= prime) {
            count += n / prime;
            n /= prime;
        }
        return count;
    }
}
