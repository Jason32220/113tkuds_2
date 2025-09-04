import java.util.Scanner;

// 定義單向鏈結串列節點
class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
    }
}

public class LC25_ReverseKGroup_Shifts {

    /**
     * 輔助函數：將鏈結串列的指定區間 [start, end) 反轉。
     * * @param head 區間的起點
     * @return 反轉後的新起點
     */
    public static ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    /**
     * 將鏈結串列每 k 個節點為一組進行反轉。
     *
     * @param head 鏈結串列的頭節點
     * @param k 每個分組的節點數
     * @return 反轉後的新頭節點
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        // 使用一個虛擬頭節點來簡化操作
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prevGroup = dummy;
        ListNode current = head;

        while (current != null) {
            ListNode end = prevGroup;
            // 檢查是否有足夠的 k 個節點
            for (int i = 0; i < k; i++) {
                end = end.next;
                if (end == null) {
                    return dummy.next;
                }
            }
            
            // 記錄下一個分組的起點
            ListNode nextGroup = end.next;
            // 斷開當前分組與後續部分的連接
            end.next = null;

            // 記錄當前分組的起點
            ListNode start = current;
            
            // 反轉當前分組
            prevGroup.next = reverse(start);
            
            // 連接反轉後的分組與下一個分組
            start.next = nextGroup;

            // 更新指針，準備處理下一個分組
            prevGroup = start;
            current = nextGroup;
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
        
        int k = scanner.nextInt();
        scanner.nextLine(); // 讀取換行符
        
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
        ListNode newHead = reverseKGroup(head, k);
        printLinkedList(newHead);

        scanner.close();
    }
}
