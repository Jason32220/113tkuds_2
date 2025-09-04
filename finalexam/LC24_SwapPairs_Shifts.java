import java.util.Scanner;

// 定義單向鏈結串列節點
class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
    }
}

public class LC24_SwapPairs_Shifts {

    /**
     * 將鏈結串列中每相鄰兩個節點進行交換。
     *
     * @param head 鏈結串列的頭節點
     * @return 交換後的新頭節點
     */
    public static ListNode swapPairs(ListNode head) {
        // 使用一個虛擬頭節點來簡化操作，特別是當需要處理原頭節點時。
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        // 檢查是否至少有兩個節點可以交換
        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;
            ListNode second = prev.next.next;

            // 執行交換
            // 1. prev 指向第二個節點
            prev.next = second;
            // 2. 第一個節點的 next 指向第二個節點的下一個節點
            first.next = second.next;
            // 3. 第二個節點的 next 指向第一個節點
            second.next = first;

            // 移動 prev 指針到下一個需要交換的「對」的前一個位置
            prev = first;
        }

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
        String line = scanner.nextLine();
        
        // 處理空輸入
        if (line.isEmpty()) {
            System.out.println("null");
            scanner.close();
            return;
        }
        
        String[] values = line.split(" ");
        int[] arr = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            arr[i] = Integer.parseInt(values[i]);
        }

        ListNode head = createLinkedList(arr);
        ListNode newHead = swapPairs(head);
        printLinkedList(newHead);

        scanner.close();
    }
}
