import java.util.*;

public class M01_BuildHeap {

    // 依照 type 選擇比較邏輯
    private static boolean compare(int a, int b, String type) {
        if (type.equals("max")) {
            return a > b; // max-heap: 父節點應 >= 子節點
        } else {
            return a < b; // min-heap: 父節點應 <= 子節點
        }
    }

    /*
     * Heapify-Down：維護以 i 為根的子樹為堆
     *
     * Time Complexity: O(log n)
     * 說明：最壞情況需一路下沉到葉節點，深度約 log n。
     */
    private static void heapify(int[] arr, int n, int i, String type) {
        int extreme = i;          // 預設當前節點為最大(或最小)
        int left = 2 * i + 1;     // 左子節點
        int right = 2 * i + 2;    // 右子節點

        // 與左子比較
        if (left < n && compare(arr[left], arr[extreme], type)) {
            extreme = left;
        }
        // 與右子比較
        if (right < n && compare(arr[right], arr[extreme], type)) {
            extreme = right;
        }

        // 若子節點違反堆性質 → 交換並遞迴修正
        if (extreme != i) {
            int temp = arr[i];
            arr[i] = arr[extreme];
            arr[extreme] = temp;
            heapify(arr, n, extreme, type);
        }
    }

    /*
     * 自底向上建堆
     *
     * Time Complexity: O(n)
     * 說明：雖然單次 heapify 為 O(log n)，但靠近葉子的節點需要調整的高度較低。
     * 經數學推導 Σ floor(n/2^h)*h = O(n)，因此整體建堆時間為線性。
     */
    private static void buildHeap(int[] arr, int n, String type) {
        // 從最後一個非葉節點開始 (n/2 - 1)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i, type);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 輸入 heap 類型 (max/min)
        String type = sc.next();
        int n = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // 建堆
        buildHeap(arr, n, type);

        // 輸出堆的陣列表示
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i]);
            if (i < n - 1) System.out.print(" ");
        }
    }
}
