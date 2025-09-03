class Solution {
    /**
     * 交換鍊錶中每對相鄰的節點。
     *
     * @param head 鍊錶的頭節點
     * @return 交換後的新鍊錶頭節點
     */
    public ListNode swapPairs(ListNode head) {
        // 創建一個虛擬頭節點，簡化對頭部節點的操作
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            // 設置要交換的兩個節點
            ListNode first = prev.next;
            ListNode second = prev.next.next;

            // 執行交換操作
            prev.next = second;
            first.next = second.next;
            second.next = first;

            // 移動 prev 指針到下一對節點的前一個位置
            prev = first;
        }

        return dummy.next;
    }
}
