import java.util.*;

public class M12_MergeKTimeTables {

    static class Node implements Comparable<Node> {
        int time, listIdx, elemIdx;
        Node(int t, int li, int ei) { time = t; listIdx = li; elemIdx = ei; }
        public int compareTo(Node o) { return this.time - o.time; }
    }

    /*
     * K-way merge using Min-Heap
     *
     * Time Complexity: O(N log K)
     * 說明：
     * - N = 總元素數量
     * - 每個元素進出 Heap 一次，Heap 大小 ≤ K → O(log K) → O(N log K)
     */
    public static List<Integer> mergeKLists(List<List<Integer>> lists) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int K = lists.size();
        for (int i = 0; i < K; i++) {
            if (!lists.get(i).isEmpty())
                pq.add(new Node(lists.get(i).get(0), i, 0));
        }

        List<Integer> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            result.add(cur.time);
            int nextIdx = cur.elemIdx + 1;
            if (nextIdx < lists.get(cur.listIdx).size()) {
                pq.add(new Node(lists.get(cur.listIdx).get(nextIdx), cur.listIdx, nextIdx));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            int len = sc.nextInt();
            List<Integer> lst = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                lst.add(sc.nextInt());
            }
            lists.add(lst);
        }

        List<Integer> merged = mergeKLists(lists);
        for (int i = 0; i < merged.size(); i++) {
            System.out.print(merged.get(i) + (i==merged.size()-1?"\n":" "));
        }
    }
}

/*
 * Overall Time Complexity: O(N log K)
 * 說明：
 * - N = 總元素數量
 * - 每個元素進出 Heap 一次，Heap 大小 ≤ K → O(log K)
 * - 適合 K 小且每列表長度中等情況
 */
