import java.util.Scanner;

// 定義單向鏈結串列節點
class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

public class LC21_MergeTwoLists_Clinics {

    /**
     * 將兩個已排序的鏈結串列合併為一個新的已排序鏈結串列。
     *
     * @param list1 第一個鏈結串列的頭節點
     * @param list2 第二個鏈結串列的頭節點
     * @return 合併後的新鏈結串列頭節點
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 使用一個虛擬頭節點（dummy node）來簡化程式碼，避免處理頭節點的特殊情況
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        // 當兩條串列都還有節點時，比較它們的值
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                // 將較小的節點接到新串列的尾部
                tail.next = list1;
                // 移動 list1 指針到下一個節點
                list1 = list1.next;
            } else {
                // 將較小的節點接到新串列的尾部
                tail.next = list2;
                // 移動 list2 指針到下一個節點
                list2 = list2.next;
            }
            // 移動新串列的尾指針
            tail = tail.next;
        }

        // 將剩餘的非空串列直接接到新串列的尾部
        if (list1 != null) {
            tail.next = list1;
        } else if (list2 != null) {
            tail.next = list2;
        }

        // 返回虛擬頭節點的下一個節點，即為合併後串列的頭部
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
        int[] arr1 = new int[n];
        for (int i = 0; i < n; i++) {
            arr1[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        int[] arr2 = new int[m];
        for (int i = 0; i < m; i++) {
            arr2[i] = scanner.nextInt();
        }

        ListNode list1 = createLinkedList(arr1);
        ListNode list2 = createLinkedList(arr2);
        
        ListNode mergedList = mergeTwoLists(list1, list2);
        printLinkedList(mergedList);

        scanner.close();
    }
}
