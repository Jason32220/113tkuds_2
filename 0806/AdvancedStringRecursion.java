import java.util.*;

public class AdvancedStringRecursion {

    // 1. 遞迴實作快速排序 (QuickSort)
    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) return; // 遞迴結束條件：只剩一個或沒有元素

        int pivot = arr[(left + right) / 2]; // 取中間值當樞軸
        int index = partition(arr, left, right, pivot);

        quickSort(arr, left, index - 1);   // 遞迴排序左半部
        quickSort(arr, index, right);      // 遞迴排序右半部
    }

    private static int partition(int[] arr, int left, int right, int pivot) {
        while (left <= right) {
            while (arr[left] < pivot) left++;
            while (arr[right] > pivot) right--;
            if (left <= right) {
                // 交換元素
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }
        return left;
    }

    // 2. 遞迴合併兩個已排序陣列
    public static int[] mergeSortedArrays(int[] a, int[] b) {
        return mergeHelper(a, b, 0, 0);
    }

    private static int[] mergeHelper(int[] a, int[] b, int i, int j) {
        // 如果 a 用完，回傳剩下的 b
        if (i == a.length) {
            return Arrays.copyOfRange(b, j, b.length);
        }
        // 如果 b 用完，回傳剩下的 a
        if (j == b.length) {
            return Arrays.copyOfRange(a, i, a.length);
        }

        // 遞迴合併
        if (a[i] < b[j]) {
            int[] rest = mergeHelper(a, b, i + 1, j);
            return prepend(a[i], rest);
        } else {
            int[] rest = mergeHelper(a, b, i, j + 1);
            return prepend(b[j], rest);
        }
    }

    private static int[] prepend(int first, int[] arr) {
        int[] result = new int[arr.length + 1];
        result[0] = first;
        System.arraycopy(arr, 0, result, 1, arr.length);
        return result;
    }

    // 3. 遞迴尋找陣列中的第 k 小元素
    // (利用 QuickSelect 演算法)
    public static int kthSmallest(int[] arr, int left, int right, int k) {
        if (left == right) return arr[left];

        int pivot = arr[(left + right) / 2];
        int index = partition(arr, left, right, pivot);

        int count = index - left; // 左半邊的元素數量

        if (k <= count) {
            return kthSmallest(arr, left, index - 1, k);
        } else {
            return kthSmallest(arr, index, right, k - count);
        }
    }

    // 4. 遞迴檢查陣列是否存在子序列總和等於目標值
    public static boolean subsetSum(int[] arr, int target, int index) {
        // 成功找到
        if (target == 0) return true;
        // 用完陣列仍沒找到
        if (index == arr.length) return false;

        // 選擇包含 arr[index]
        if (subsetSum(arr, target - arr[index], index + 1)) return true;
        // 選擇不包含 arr[index]
        return subsetSum(arr, target, index + 1);
    }

    // 測試
    public static void main(String[] args) {
        // 測試 QuickSort
        int[] arr = {5, 3, 8, 4, 2, 7};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("QuickSort 排序結果: " + Arrays.toString(arr));

        // 測試遞迴合併
        int[] a = {1, 3, 5, 7};
        int[] b = {2, 4, 6, 8};
        System.out.println("合併結果: " + Arrays.toString(mergeSortedArrays(a, b)));

        // 測試第 k 小
        int[] arr2 = {7, 10, 4, 3, 20, 15};
        int k = 3;
        System.out.println("第 " + k + " 小的元素是: " + kthSmallest(arr2, 0, arr2.length - 1, k));

        // 測試子序列和
        int[] arr3 = {3, 34, 4, 12, 5, 2};
        int target = 9;
        System.out.println("是否存在子序列總和 = " + target + " ? " + subsetSum(arr3, target, 0));
    }
}
