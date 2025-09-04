import java.util.Scanner;

// 定義單向鏈結串列節點
class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

public class LC19_RemoveNth_Node_Clinic {

    /**
     * 在一次遍歷中刪除鏈結串列的倒數第 k 個節點。
     *
     * @param head 鏈結串列的頭節點
     * @param k 倒數第 k 個節點
     * @return 刪除後的鏈結串列新頭節點
     */
    public static ListNode removeNthFromEnd(ListNode head, int k) {
        // 使用一個虛擬頭節點（dummy node）可以簡化對頭節點的處理，特別是當需要刪除頭節點時。
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy;
        ListNode slow = dummy;

        // 快指針先走 k 步
        // 這樣 fast 和 slow 之間就相隔了 k 個節點
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }

        // 同步移動快慢指針，直到快指針到達鏈結串列末尾
        // 此時慢指針將位於待刪除節點的前一個位置
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 刪除倒數第 k 個節點
        slow.next = slow.next.next;

        // 返回新串列的頭節點
        return dummy.next;
    }

    // 輔助函數：將陣列轉換為鏈結串列
    public static ListNode createLinkedList(int[] arr) {
        if (arr.length == 0) {
            return null;
        }
        ListNode head = new ListNode(arr[0]);
        ListNode current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        return head;
    }

    // 輔助函數：列印鏈結串列
    public static void printLinkedList(ListNode head) {
        if (head == null) {
            System.out.println("null");
            return;
        }
        ListNode current = head;
        StringBuilder sb = new StringBuilder();
        while (current != null) {
            sb.append(current.val).append(" ");
            current = current.next;
        }
        System.out.println(sb.toString().trim());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int k = scanner.nextInt();

        ListNode head = createLinkedList(arr);
        ListNode newHead = removeNthFromEnd(head, k);
        printLinkedList(newHead);

        scanner.close();
    }
}
