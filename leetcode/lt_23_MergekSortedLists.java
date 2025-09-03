import java.util.PriorityQueue;


class Solution {
    /**
     * 合併 k 個已排序的鍊錶。
     *
     * @param lists 包含 k 個鍊錶頭節點的陣列
     * @return 合併後的新鍊錶頭節點
     */
    public ListNode mergeKLists(ListNode[] lists) {
        // 如果輸入的鍊錶陣列為空，直接返回空
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        // 創建一個最小堆（優先佇列），用於儲存鍊錶節點，並按照節點值排序
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        // 遍歷所有鍊錶，將每個非空的頭節點加入最小堆
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.add(list);
            }
        }

        // 創建一個虛擬頭節點，簡化合併後的鍊錶構建
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        // 當最小堆不為空時，持續從中取出最小節點
        while (!minHeap.isEmpty()) {
            // 從堆中取出值最小的節點
            ListNode smallestNode = minHeap.poll();

            // 將該節點連接到新鍊錶的末尾
            current.next = smallestNode;
            current = current.next;

            // 如果該節點還有下一個節點，將其加入堆中
            if (smallestNode.next != null) {
                minHeap.add(smallestNode.next);
            }
        }

        return dummy.next;
    }
}
