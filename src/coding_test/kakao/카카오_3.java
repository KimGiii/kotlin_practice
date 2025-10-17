package coding_test.kakao;

import java.util.Scanner;

public class 카카오_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int maxDistributorNodes = sc.nextInt();   // dist_limit
        int splitLimit = sc.nextInt();            // split_limit
        sc.close();

        System.out.println(Solution.maxLeafCount(maxDistributorNodes, splitLimit));
    }

    class Solution {
        /*
        루트 노드, 리프 노드, 분배 노드로 구성되는 트리가 존재
        루트노드는 자식 노드를 하나만 가지며, 루트 노드가 아닌 노드는 자식 노드를 2개, 3개 또는 0개 가질 수 있다.
        이때 자식 노드가 0인 노드는 리프노드
        제한된 조건 아래 트리를 하나 구성하여 리프노드를 가능한 많이 만든다.

        트리 구성 규칙
        1. 루트 노드와 리프 노드를 제외한 나머지 노드를 분배 노드라고 하며, 분배 노드는 자식 노드를 2개 또는 3개 가짐
        2. 분배 노드는 최대 dist_limit 개 존재할 수 있음
        3. 트리에서 같은 깊이에 있는 분배 노드의 자식 노드 수는 모두 같아야 함, 노드의 깊이는 루트 노드부터 해당 노드까지의 최단 경로 길이와 같음
        4. 모든 리프 노드는 분배도라는 값을 가짐
            1. 분배도는 해당 리프 노드의 부모 노드에서 루트 노드까지의 최단 경로 상에 있는 모든 노드의 자식 노드 개수의 곱
        5. 모든 리프 노드의 분배도는 split_limit보다 작거나 같아야함

        제한사항
        1. 0 <= dist_limit <= 10^9
        2. 1 <= split_limit <= 10^9
         */

        // 리프 노드 수 최대값을 int로 반환
        public static int maxLeafCount(int maxDistributorNodes, int splitLimit) {
            if (maxDistributorNodes == 0 || splitLimit <= 1) return 1;

            long remainDist = maxDistributorNodes; // 남은 분배 노드 수
            long pathProduct = 1;                  // 루트→현재 레벨까지 분배도 곱
            long frontier = 1;                     // 현재 레벨의 노드 수
            long leafGainSum = 0;                  // Σ k_i (b_i - 1)

            while (remainDist > 0 && frontier > 0) {
                boolean can3 = pathProduct * 3 <= splitLimit;
                boolean can2 = pathProduct * 2 <= splitLimit;

                if (!can2 && !can3) break;

                int branching;
                if (can2 && can3) {
                    // 한 단계 앞까지 분배도 여유를 본 의사결정
                    if (pathProduct * 9 <= splitLimit) branching = 3;     // 다음 레벨도 3 가능
                    else if (pathProduct * 6 <= splitLimit) branching = 2; // 다음 레벨에서 3 가능하도록 유도
                    else branching = 3;                                   // 즉시 이득 극대화
                } else {
                    branching = can3 ? 3 : 2;
                }

                long useAsDistributor = Math.min(frontier, remainDist); // 이 레벨에서 분배로 쓰는 개수 k
                leafGainSum += useAsDistributor * (branching - 1);

                remainDist -= useAsDistributor;
                pathProduct *= branching;
                frontier = useAsDistributor * branching;
            }

            long leaves = 1 + leafGainSum;
            return leaves > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) leaves;
        }
    }
}
