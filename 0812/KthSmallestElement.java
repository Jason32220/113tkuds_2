import java.util.Comparator;
import java.util.PriorityQueue;

public class KthSmallestElement {

    // 方法 1：Max Heap 大小為 K
    public static int kthSmallestMaxHeap(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder()); // 大根堆

        for (int num : arr) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) {
                maxHeap.poll(); // 保持大小為 K
            }
        }
        return maxHeap.peek(); // 堆頂就是第 K 小
    }

    // 方法 2：Min Heap 提取 K 次
    public static int kthSmallestMinHeap(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 小根堆
        for (int num : arr) {
            minHeap.offer(num);
        }
        int result = -1;
        for (int i = 0; i < k; i++) {
            result = minHeap.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] testArrays = {
            {7, 10, 4, 3, 20, 15},
            {1},
            {3, 1, 4, 1, 5, 9, 2, 6}
        };
        int[] ks = {3, 1, 4};

        for (int i = 0; i < testArrays.length; i++) {
            int[] arr = testArrays[i];
            int k = ks[i];

            System.out.println("陣列: " + java.util.Arrays.toString(arr) + ", K = " + k);

            long start1 = System.nanoTime();
            int ans1 = kthSmallestMaxHeap(arr, k);
            long time1 = System.nanoTime() - start1;

            long start2 = System.nanoTime();
            int ans2 = kthSmallestMinHeap(arr, k);
            long time2 = System.nanoTime() - start2;

            System.out.println("方法1 (Max Heap): " + ans1 + "，耗時 " + time1 + " ns");
            System.out.println("方法2 (Min Heap): " + ans2 + "，耗時 " + time2 + " ns");
            System.out.println();
        }
    }
}
