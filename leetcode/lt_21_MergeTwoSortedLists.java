/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    /**
     * 尋找所有 concatenated substrings 的起始索引。
     *
     * @param s 輸入字串
     * @param words 單詞陣列
     * @return 所有 concatenated substrings 的起始索引列表
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 創建一個虛擬頭節點，簡化邊界情況處理
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        // 迭代遍歷兩個鍊錶，直到其中一個為空
        while (list1 != null && list2 != null) {
            // 比較兩個鍊錶當前節點的值
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            // 移動當前指針到新連接的節點
            current = current.next;
        }

        // 處理其中一個鍊錶還有剩餘節點的情況
        if (list1 != null) {
            current.next = list1;
        } else if (list2 != null) {
            current.next = list2;
        }

        return dummy.next;
    }
}
