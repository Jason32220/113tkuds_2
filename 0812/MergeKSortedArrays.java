import java.util.*;

public class MergeKSortedArrays {

    // 用來封裝堆中元素資訊
    static class ArrayEntry implements Comparable<ArrayEntry> {
        int value;      // 元素值
        int arrayIdx;   // 是第幾個陣列
        int elementIdx; // 該陣列的第幾個元素

        public ArrayEntry(int value, int arrayIdx, int elementIdx) {
            this.value = value;
            this.arrayIdx = arrayIdx;
            this.elementIdx = elementIdx;
        }

        @Override
        public int compareTo(ArrayEntry other) {
            return Integer.compare(this.value, other.value);
        }
    }

    public static List<Integer> mergeKSortedArrays(int[][] arrays) {
        PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<>();
        List<Integer> result = new ArrayList<>();

        // 初始：將每個陣列的第一個元素加入堆
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.offer(new ArrayEntry(arrays[i][0], i, 0));
            }
        }

        // 持續取堆頂最小元素，並加入該元素所在陣列的下一個元素
        while (!minHeap.isEmpty()) {
            ArrayEntry current = minHeap.poll();
            result.add(current.value);

            int nextIdx = current.elementIdx + 1;
            if (nextIdx < arrays[current.arrayIdx].length) {
                minHeap.offer(new ArrayEntry(arrays[current.arrayIdx][nextIdx], current.arrayIdx, nextIdx));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] test1 = {{1,4,5}, {1,3,4}, {2,6}};
        int[][] test2 = {{1,2,3}, {4,5,6}, {7,8,9}};
        int[][] test3 = {{1}, {0}};

        System.out.println(mergeKSortedArrays(test1)); // [1,1,2,3,4,4,5,6]
        System.out.println(mergeKSortedArrays(test2)); // [1,2,3,4,5,6,7,8,9]
        System.out.println(mergeKSortedArrays(test3)); // [0,1]
    }
}
