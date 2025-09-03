class Solution {
    /**
     * 從鍊錶末尾移除第 n 個節點。
     * * @param head 鍊錶的頭節點
     * @param n 要移除的節點位置（從末尾數）
     * @return 處理後鍊錶的頭節點
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 創建一個虛擬頭節點，簡化移除頭節點的邊界情況
        ListNode dummy = new ListNode(0, head);
        ListNode slow = dummy;
        ListNode fast = dummy;

        // 快指針先向前移動 n+1 步，這樣快慢指針之間形成 n 個節點的間距
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // 同步移動快慢指針，直到快指針到達末尾
        // 此時慢指針將指向要移除節點的前一個節點
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // 執行移除操作：將慢指針的 next 指向要移除節點的下一個節點
        slow.next = slow.next.next;

        // 返回虛擬頭節點的下一個節點，即為新的頭節點
        return dummy.next;
    }
}
