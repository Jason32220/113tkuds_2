import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

// 定義單向鏈結串列節點
class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
    }
}

public class LC23_MergeKLists_Hospitals {

    /**
     * 將 k 個已排序的鏈結串列合併為一個新的已排序鏈結串列。
     * * @param lists 包含 k 個鏈結串列頭節點的陣列
     * @return 合併後的新鏈結串列頭節點
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        // 處理邊界情況
        if (lists == null || lists.length == 0) {
            return null;
        }

        // 建立一個最小堆（優先佇列），用於儲存每個串列的當前頭節點
        // 比較器 (a, b) -> a.val - b.val 確保堆頂是值最小的節點
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        // 將所有非空的鏈結串列頭節點放入最小堆中
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.add(node);
            }
        }

        // 使用虛擬頭節點和尾指針來構建新的鏈結串列
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        // 當最小堆不為空時，重複以下步驟
        while (!minHeap.isEmpty()) {
            // 從堆中取出值最小的節點
            ListNode smallest = minHeap.poll();

            // 將這個最小節點接到新串列的尾部
            tail.next = smallest;
            tail = tail.next;

            // 如果取出的節點還有下一個節點，將其放入堆中
            if (smallest.next != null) {
                minHeap.add(smallest.next);
            }
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
        // 為了處理換行符，讀取下一行
        scanner.nextLine();

        List<ListNode> listNodes = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            String line = scanner.nextLine();
            String[] values = line.split(" ");
            
            // 處理空串列
            if (values.length == 1 && values[0].equals("-1")) {
                listNodes.add(null);
                continue;
            }

            // 處理以 -1 結尾的輸入
            List<Integer> tempArr = new ArrayList<>();
            for (String valStr : values) {
                if (!valStr.equals("-1")) {
                    tempArr.add(Integer.parseInt(valStr));
                }
            }

            int[] arr = tempArr.stream().mapToInt(Integer::intValue).toArray();
            listNodes.add(createLinkedList(arr));
        }

        ListNode[] lists = listNodes.toArray(new ListNode[0]);
        
        ListNode mergedList = mergeKLists(lists);
        printLinkedList(mergedList);

        scanner.close();
    }
}
