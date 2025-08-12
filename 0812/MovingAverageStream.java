import java.util.*;

public class MovingAverageStream {
    private final int size;
    private final Queue<Integer> window; // 保持視窗元素順序
    private long sum; // 視窗總和，用於計算平均

    // 用於中位數計算的兩個堆
    private PriorityQueue<Integer> maxHeap; // 小的一半 (最大堆)
    private PriorityQueue<Integer> minHeap; // 大的一半 (最小堆)

    // 延遲刪除結構
    private Map<Integer, Integer> delayed;

    // 用於快速取得最小值與最大值
    private TreeMap<Integer, Integer> freqMap; // key=數字, value=出現次數

    // 視窗大小中堆元素數量計數
    private int maxHeapSize;
    private int minHeapSize;

    public MovingAverageStream(int size) {
        this.size = size;
        this.window = new LinkedList<>();
        this.sum = 0;

        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
        delayed = new HashMap<>();
        freqMap = new TreeMap<>();

        maxHeapSize = 0;
        minHeapSize = 0;
    }

    // 新增元素並回傳移動平均
    public double next(int val) {
        window.offer(val);
        sum += val;

        // 新元素加入中位數兩堆
        if (maxHeap.isEmpty() || val <= maxHeap.peek()) {
            maxHeap.offer(val);
            maxHeapSize++;
        } else {
            minHeap.offer(val);
            minHeapSize++;
        }

        // 加入freqMap
        freqMap.put(val, freqMap.getOrDefault(val, 0) + 1);

        balanceHeaps();

        // 超過視窗大小時刪除最舊元素
        if (window.size() > size) {
            int out = window.poll();
            sum -= out;
            // 延遲刪除out
            delayed.put(out, delayed.getOrDefault(out, 0) + 1);

            // 從freqMap扣除
            int count = freqMap.get(out);
            if (count == 1) freqMap.remove(out);
            else freqMap.put(out, count - 1);

            // 判斷out在哪個堆，減少對應size
            if (!maxHeap.isEmpty() && out <= maxHeap.peek()) {
                maxHeapSize--;
                if (out == maxHeap.peek()) prune(maxHeap);
            } else {
                minHeapSize--;
                if (!minHeap.isEmpty() && out == minHeap.peek()) prune(minHeap);
            }

            balanceHeaps();
        }

        return (double) sum / window.size();
    }

    // 取得中位數
    public double getMedian() {
        if (window.isEmpty()) return 0;
        if ((window.size() & 1) == 1) { // 奇數個元素
            return maxHeap.peek();
        } else {
            return ((double) maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }

    // 取得最小值
    public int getMin() {
        if (freqMap.isEmpty()) return 0;
        return freqMap.firstKey();
    }

    // 取得最大值
    public int getMax() {
        if (freqMap.isEmpty()) return 0;
        return freqMap.lastKey();
    }

    // 調整兩堆大小平衡，maxHeap最多比minHeap多1個元素
    private void balanceHeaps() {
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

    // 清理延遲刪除的元素
    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty()) {
            int num = heap.peek();
            if (delayed.containsKey(num)) {
                delayed.put(num, delayed.get(num) - 1);
                if (delayed.get(num) == 0) delayed.remove(num);
                heap.poll();
            } else {
                break;
            }
        }
    }

    // 測試
    public static void main(String[] args) {
        MovingAverageStream ma = new MovingAverageStream(3);
        System.out.println(ma.next(1));   // 1.0
        System.out.println(ma.next(10));  // 5.5
        System.out.println(ma.next(3));   // 4.666...
        System.out.println(ma.next(5));   // 6.0
        System.out.println("Median: " + ma.getMedian()); // 5.0
        System.out.println("Min: " + ma.getMin());       // 3
        System.out.println("Max: " + ma.getMax());       // 10
    }
}
