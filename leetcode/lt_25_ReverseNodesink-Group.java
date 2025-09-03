import java.util.PriorityQueue;

/**
 * 定義單向鍊錶的節點。
 */
public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

/**
 * 解決以 k 個節點為一組來反轉鍊錶的問題。
 * 核心思路：迭代法，搭配虛擬頭節點和三個指針進行反轉。
 */
class Solution {

    /**
     * 以 k 個節點為一組反轉鍊錶，並返回新的頭節點。
     * @param head 鍊錶的頭節點
     * @param k 每組要反轉的節點數量
     * @return 反轉後的鍊錶頭節點
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // 為了處理反轉後的新頭節點，我們使用一個虛擬頭節點
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode end = dummy;

        // 只要當前組還有 k 個節點，我們就進行反轉
        while (end.next != null) {
            // 找到每組的結束點 (end)
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }

            // 如果鍊錶剩餘的節點不足 k 個，則不需要反轉，直接跳出迴圈
            if (end == null) {
                break;
            }

            // 開始反轉這一組的節點
            ListNode start = prev.next;
            ListNode nextGroup = end.next;
            end.next = null; // 斷開與下一組的連接

            // 反轉從 start 到 end 的鍊錶
            prev.next = reverse(start);

            // 重新連接反轉後的鍊錶
            start.next = nextGroup;

            // 移動 prev 和 end 指針到下一個組的起點
            prev = start;
            end = start;
        }

        return dummy.next;
    }

    /**
     * 輔助函式：反轉一個單向鍊錶
     * @param head 要反轉的鍊錶頭節點
     * @return 反轉後的鍊錶頭節點
     */
    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode nextTemp = current.next;
            current.next = prev;
            prev = current;
            current = nextTemp;
        }
        return prev;
    }
}
