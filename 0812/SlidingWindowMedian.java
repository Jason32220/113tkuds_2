import java.util.*;

public class SlidingWindowMedian {

    private PriorityQueue<Integer> maxHeap; // 儲存較小一半，最大堆（Java默認是小根堆，故用負號技巧）
    private PriorityQueue<Integer> minHeap; // 儲存較大一半，最小堆

    private Map<Integer, Integer> delayed; // 延遲刪除計數器
    private int maxHeapSize, minHeapSize;

    public SlidingWindowMedian() {
        // maxHeap 用反向比較器做最大堆
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
        delayed = new HashMap<>();
        maxHeapSize = 0;
        minHeapSize = 0;
    }

    // 加入元素
    private void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
            maxHeapSize++;
        } else {
            minHeap.offer(num);
            minHeapSize++;
        }
        balanceHeaps();
    }

    // 延遲刪除元素（視窗滑動時要移除的元素）
    private void removeNum(int num) {
        delayed.put(num, delayed.getOrDefault(num, 0) + 1);

        // 依據 num 與 maxHeap.peek() 比較判斷哪邊要減少元素數量
        if (!maxHeap.isEmpty() && num <= maxHeap.peek()) {
            maxHeapSize--;
            if (num == maxHeap.peek()) {
                prune(maxHeap);
            }
        } else {
            minHeapSize--;
            if (!minHeap.isEmpty() && num == minHeap.peek()) {
                prune(minHeap);
            }
        }
        balanceHeaps();
    }

    // 調整兩個 heap 大小平衡
    private void balanceHeaps() {
        // maxHeap 可能比 minHeap 多最多 1 個元素
        if (maxHeapSize > minHeapSize + 1) {
            minHeap.offer(maxHeap.poll());
            maxHeapSize--;
            minHeapSize++;
            prune(maxHeap);
        } else if (maxHeapSize < minHeapSize) {
            maxHeap.offer(minHeap.poll());
            maxHeapSize++;
            minHeapSize--;
            prune(minHeap);
        }
    }

    // 清除 heap 頂端被延遲刪除的元素
    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty()) {
            int num = heap.peek();
            if (delayed.containsKey(num)) {
                delayed.put(num, delayed.get(num) - 1);
                if (delayed.get(num) == 0) {
                    delayed.remove(num);
                }
                heap.poll();
            } else {
                break;
            }
        }
    }

    // 取得中位數
    private double getMedian(int k) {
        if (k % 2 == 1) {
            return maxHeap.peek();
        } else {
            return ((double) maxHeap.peek() + minHeap.peek()) / 2;
        }
    }

    // 主程式：計算每個滑動視窗的中位數
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        double[] result = new double[n - k + 1];
        for (int i = 0; i < n; i++) {
            addNum(nums[i]);
            if (i >= k - 1) {
                result[i - k + 1] = getMedian(k);
                removeNum(nums[i - k + 1]);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SlidingWindowMedian solver = new SlidingWindowMedian();

        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int k1 = 3;
        System.out.println(Arrays.toString(solver.medianSlidingWindow(nums1, k1)));
        // [1.0, -1.0, -1.0, 3.0, 5.0, 6.0]

        int[] nums2 = {1, 2, 3, 4};
        int k2 = 2;
        System.out.println(Arrays.toString(solver.medianSlidingWindow(nums2, k2)));
        // [1.5, 2.5, 3.5]
    }
}
