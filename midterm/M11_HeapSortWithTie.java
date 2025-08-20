import java.util.*;

public class M11_HeapSortWithTie {

    static class Pair {
        int score, index;
        Pair(int s, int i) { score = s; index = i; }
    }

    /*
     * Max-Heapify
     *
     * Time Complexity: O(log n)
     * 說明：
     * - 每次堆化沿著樹向下走，最壞為樹高 log n
     */
    private static void heapify(Pair[] arr, int n, int i) {
        int largest = i;
        int left = 2*i + 1, right = 2*i + 2;

        if (left < n) {
            if (arr[left].score > arr[largest].score ||
                (arr[left].score == arr[largest].score && arr[left].index < arr[largest].index))
                largest = left;
        }

        if (right < n) {
            if (arr[right].score > arr[largest].score ||
                (arr[right].score == arr[largest].score && arr[right].index < arr[largest].index))
                largest = right;
        }

        if (largest != i) {
            Pair tmp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = tmp;
            heapify(arr, n, largest);
        }
    }

    /*
     * Build Max-Heap
     *
     * Time Complexity: O(n)
     * 說明：
     * - 自底向上堆化所有非葉節點 → O(n)
     */
    private static void buildHeap(Pair[] arr, int n) {
        for (int i = n/2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
    }

    /*
     * Heap Sort
     *
     * Time Complexity: O(n log n)
     * 說明：
     * - BuildHeap O(n)
     * - n 次交換 + 每次 heapify O(log n) → O(n log n)
     */
    private static void heapSort(Pair[] arr) {
        int n = arr.length;
        buildHeap(arr, n);

        for (int i = n-1; i >= 1; i--) {
            Pair tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            heapify(arr, i, 0);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Pair[] arr = new Pair[n];
        for (int i = 0; i < n; i++) {
            int s = sc.nextInt();
            arr[i] = new Pair(s, i);
        }

        heapSort(arr);

        // 輸出升序
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i].score + (i==n-1?"\n":" "));
        }
    }
}

/*
 * Overall Time Complexity: O(n log n)
 * 說明：
 * - BuildHeap O(n)
 * - n 次 Heapify，每次 O(log n) → O(n log n)
 * - 使用 Pair(score,index) 解決平手順序
 */
