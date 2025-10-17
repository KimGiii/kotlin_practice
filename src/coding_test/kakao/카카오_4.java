package coding_test.kakao;

import java.util.*;

public class 카카오_4 {
    /*
    1번부터 n번까지 번호가 붙은 n개 배양체를 n-1개의 파이프로 이어 하나의 트리 모양을 만듬
    각  파이프는 a, b, c 3개의 종류, 초기에는 모든 파이프가 닫혀있음
    infection으로 주어진 번호의 배양체는 감염되어있음, 감염된 배양체는 열린 파이프를 통해 다른 인접 배양체를 감염시킴
    같은 종류의 파이프를 한꺼번에 열었다 닫을 수 있음, 단 한 종류의 파이프를 연 후에 다시 닫기 전에 다른 종류의 파이프 열 수 없음
    이를 총 k번 반복하여 최대한 많은 배양체를 감염시킨다.

    배양체의 개수를 나타내는 정수 n, 감염된 배양체의 노드 번호를 나타내는 정수 infection,
    파이프 정보를 나타내는 2차원 정수 배열 edges, 최대 행동 수를 나타내는 정수 k가 매개변수로 주어짐.

    최대 k번 파이프를 열었다 닫은 후 감염된 배양체 개수의 최대값을 return

    제한사항
    1. 2 <= n <= 100
    2. 1 <= infection <= n
    3. edges.length = n-1
        1. edges[i]는 [x, y, type] 형태로 x번 노드와 y번 노드 사이가 type 종류의 파이프로 연결되어 있음을 의미
        2. 1 <= x < y <= n
        3. 1 <= type <= 3
            1. 1은 a, 2는 b, 3은 c를 나타냄
    4. 1 <= k <= 10
     */

    public static void main(String[] args) {
        int n = 10, infection = 1, k = 2;
        int[][] edges = {
                {1, 2, 1},
                {1, 3, 1},
                {1, 4, 3},
                {1, 5, 2},
                {5, 6, 1},
                {5, 7, 1},
                {2, 8, 3},
                {2, 9, 2},
                {9, 10, 1},
        };
        System.out.println(Solution.solution(n, infection, edges, k)); // 출력: 최대 감염 수
    }

    class Solution {
        static class Edge {
            int to, color;
            Edge(int t, int c) { to = t; color = c; }
        }

        // 입력: n, infection(1-indexed), edges: [x,y,type], k
        // 출력: 최대 감염 수
        public static int solution(int n, int infection, int[][] edges, int k) {
            if (k <= 0) return 1; // 시작 노드만
            List<Edge>[] graph = buildGraph(n, edges);

            // 1) 루트(infection) 기준 parent, colorToParent 획득
            int[] parent = new int[n + 1];
            int[] typeToParent = new int[n + 1];
            Arrays.fill(parent, -1);
            bfsSetParent(graph, infection, parent, typeToParent);

            // 2) 각 노드 v의 run 시퀀스 R[v] 전처리
            int[][] runs = new int[n + 1][];
            for (int v = 1; v <= n; v++) {
                runs[v] = buildRunSequence(v, infection, parent, typeToParent);
            }

            // 3) 길이 1..k, 인접 중복 없는 색 시퀀스 S 전부 열거하며 최대 감염 수 계산
            int[] best = {1}; // 최소 시작 노드 1개
            List<Integer> cur = new ArrayList<>();
            for (int len = 1; len <= k; len++) {
                dfsEnumerateSequences(len, cur, best, runs, k, n);
            }
            return best[0];
        }

        private static List<Edge>[] buildGraph(int n, int[][] edges) {
            @SuppressWarnings("unchecked")
            List<Edge>[] g = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) g[i] = new ArrayList<>();
            for (int[] e : edges) {
                int x = e[0], y = e[1], c = e[2];
                g[x].add(new Edge(y, c));
                g[y].add(new Edge(x, c));
            }
            return g;
        }

        private static void bfsSetParent(List<Edge>[] g, int root, int[] parent, int[] colorToParent) {
            Queue<Integer> q = new ArrayDeque<>();
            parent[root] = 0; // root 표시
            q.add(root);
            while (!q.isEmpty()) {
                int u = q.poll();
                for (Edge ed : g[u]) {
                    int v = ed.to;
                    if (parent[v] == -1) {
                        parent[v] = u;
                        colorToParent[v] = ed.color;
                        q.add(v);
                    }
                }
            }
        }

        // v→root 색들을 역추적 후 뒤집고 인접 동일 색을 1개로 압축
        private static int[] buildRunSequence(int v, int root, int[] parent, int[] colorToParent) {
            if (v == root) return new int[0]; // 시작점은 빈 시퀀스(라운드 0)
            List<Integer> pathColors = new ArrayList<>();
            int cur = v;
            while (cur != root) {
                pathColors.add(colorToParent[cur]);
                cur = parent[cur];
            }
            Collections.reverse(pathColors);
            // run-length 압축(값만 유지)
            List<Integer> runs = new ArrayList<>();
            Integer prev = null;
            for (int c : pathColors) {
                if (prev == null || prev != c) {
                    runs.add(c);
                    prev = c;
                }
            }
            // 배열로 변환
            int[] arr = new int[runs.size()];
            for (int i = 0; i < arr.length; i++) arr[i] = runs.get(i);
            return arr;
        }

        // 길이 targetLen의 색 시퀀스 S 열거(연속 중복 금지)
        private static void dfsEnumerateSequences(int targetLen, List<Integer> cur,
                                                  int[] best, int[][] runs, int k, int n) {
            int L = cur.size();
            if (L == targetLen) {
                int infected = evaluate(cur, runs, k, n);
                if (infected > best[0]) best[0] = infected;
                return;
            }
            for (int c = 1; c <= 3; c++) {
                if (L > 0 && cur.get(L - 1) == c) continue; // 인접 중복 금지
                cur.add(c);
                dfsEnumerateSequences(targetLen, cur, best, runs, k, n);
                cur.remove(cur.size() - 1);
            }
        }

        // 시퀀스 S로 감염 가능한 노드 수 계산
        private static int evaluate(List<Integer> S, int[][] runs, int k, int n) {
            int cnt = 0;
            for (int v = 1; v <= n; v++) {
                int[] R = runs[v];
                if (R.length > k) continue; // 라운드 초과는 불가
                if (isSubsequence(R, S)) cnt++;
            }
            return cnt;
        }

        // 투포인터 대신 간단 그리디 부분수열 검사(길이 작아 충분)
        private static boolean isSubsequence(int[] R, List<Integer> S) {
            int i = 0;
            for (int c : S) {
                if (i < R.length && R[i] == c) i++;
            }
            return i == R.length;
        }
    }


}
