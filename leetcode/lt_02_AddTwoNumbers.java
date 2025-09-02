// 類別名稱必須是 'Solution'，以符合線上測試平台的要求
class Solution {
    
    /**
     * 此方法將兩個以鏈結串列表示的非負整數相加。
     * 數字的位數是以反向順序儲存的。
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 創建一個假的頭部節點 (dummyHead)，用來簡化新鏈結串列的建立。
        // 最終的結果會從這個節點的下一個開始。
        ListNode dummyHead = new ListNode(0);
        // current 指標會指向新鏈結串列的當前節點，用於逐一添加新節點。
        ListNode current = dummyHead;
        // carry 變數用於儲存進位，初始為 0。
        int carry = 0;

        // 當 l1、l2 還有節點或還有進位時，迴圈繼續
        while (l1 != null || l2 != null || carry != 0) {
            // 從 l1 取得當前節點的值。如果 l1 已經為空（即遍歷完畢），則視為 0。
            int x = (l1 != null) ? l1.val : 0;
            // 從 l2 取得當前節點的值。如果 l2 已經為空，則視為 0。
            int y = (l2 != null) ? l2.val : 0;

            // 計算當前位的總和：l1 的值 + l2 的值 + 來自上一位的進位。
            int sum = x + y + carry;

            // 計算下一個位的進位：總和除以 10 的整數部分。
            carry = sum / 10;
            // 創建一個新的節點，其值為總和除以 10 的餘數（即當前位的數字）。
            current.next = new ListNode(sum % 10);
            // 將 current 指標移動到新創建的節點，準備添加下一個節點。
            current = current.next;

            // 將 l1 和 l2 指標往前推進，如果它們不為空的話。
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        
        // 返回假頭節點的下一個節點，這就是我們最終的結果鏈結串列的開頭。
        return dummyHead.next;
    }
}